package me.justramon.ircbot.justabotx.features.gamemode.games;

import org.pircbotx.hooks.events.MessageEvent;

import me.justramon.ircbot.justabotx.core.Core;
import me.justramon.ircbot.justabotx.features.gamemode.IGame;
import me.justramon.ircbot.justabotx.util.MessageHandler;

public class TicTacToe implements IGame
{
	private String winner;
	private String[] board;
	private boolean xTurn;
	private boolean finished;
	private int filledFields;

	@Override
	public void restart(String channel) throws Exception
	{
		Core.bot.sendIRC().message(channel, "The field is labeled 1-9 from top left to bottom right. Command: @set <label> (@set 5 would set your symbol to the middle field)");
		Core.bot.sendIRC().message(channel, "Decide who of you is playing X and who is playing O, then let player X start the game.");
		filledFields = 0;
		finished = false;
		board = new String[]{"-", "-", "-", "-", "-", "-", "-", "-", "-"};
		xTurn = true;
		sendBoard(channel);
		Core.bot.sendIRC().message(channel, "It's X's turn!");
	}

	@Override
	public void exeCommand(String cmdName, MessageEvent event, String[] args) throws Exception
	{
		String channel = event.getChannel().getName();

		if(finished)
		{
			Core.bot.sendIRC().message(channel, "This game is over!");
			return;
		}

		int field = Integer.parseInt(args[0]);

		if(field < 1 || field > 9)
		{
			Core.bot.sendIRC().message(channel, "You can only enter numbers from 1 to 9, 1 being the top-left spot on the board.");
			return;
		}

		try
		{
			if(args.length == 1)
			{
				if(board[--field].equals("-"))
				{
					board[field] = xTurn ? "X" : "O";
					filledFields++;
					xTurn = !xTurn;
					sendBoard(channel);

					if(!(winner = checkWin()).equals(""))
					{
						end(channel, "Congratulations, " + winner + "! You won!");
					}
					else if(filledFields == 9)
					{
						end(channel, "The game ended in a tie!");
					}
					else
					{
						Core.bot.sendIRC().message(channel, "It's " + (xTurn ? "X" : "O") + "'s turn!");
					}
				}
				else
				{
					Core.bot.sendIRC().message(channel, "This place is already full!");
				}
			}
			else
			{
				Core.bot.sendIRC().message(channel, "Please enter a number of a spot to place your symbol on.");
			}
		}
		catch(NumberFormatException e)
		{
			Core.bot.sendIRC().message(channel, "That is not a number.");
		}
	}

	@Override
	public String getName()
	{
		return "TicTacToe";
	}

	@Override
	public String getVersion()
	{
		return "1.1";
	}

	@Override
	public String[] getCommands()
	{
		return new String[]{"set"};
	}

	private void end(String channel, String msg)
	{
		MessageHandler.sendChannelMessage(channel, msg);
		Core.bot.send().message(channel, "Use @disable to disable the game, or use @restart for another round!");
		finished = true;
	}
	
	/**
	 * @return X or O if someone has won, null otherwise
	 */
	private String checkWin()
	{
		String winner = "";

		//all winning conditions
		if(board[0] == board[1] && board[1] == board[2])
		{
			winner = board[0];
		}
		else if(board[3] == board[4] && board[4] == board[5])
		{
			winner = board[3];
		}
		else if(board[6] == board[7] && board[7] == board[8])
		{
			winner = board[6];
		}
		else if(board[0] == board[3] && board[3] == board[6])
		{
			winner = board[0];
		}
		else if(board[1] == board[4] && board[4] == board[7])
		{
			winner = board[1];
		}
		else if(board[2] == board[5] && board[5] == board[8])
		{
			winner = board[2];
		}
		else if(board[0] == board[4] && board[4] == board[8])
		{
			winner = board[0];
		}
		else if(board[2] == board[4] && board[4] == board[6])
		{
			winner = board[2];
		}
		else
		{
			winner = "";
		}

		return winner.equals("-") ? "" : winner;
	}

	private void sendBoard(String channel)
	{
		String output = "";
		int count = 0;

		for(String s : board)
		{
			output += s;
			count++;

			if(count == 3)
			{
				Core.bot.sendIRC().message(channel, output);
				output = "";
				count = 0;
			}
		}
	}
}
