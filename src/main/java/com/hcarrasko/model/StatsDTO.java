package com.hcarrasko.model;

public class StatsDTO {
    private ChessDaily chess_daily;
    private Chess960Daily chess960_daily;
    private ChessRapid chess_rapid;
    private ChessBullet chess_bullet;
    private ChessBlitz chess_blitz;
    private Tactics tactics;
    private Lessons lessons;
    
    //private PuzzleRush puzzle_rush;
	
	public class ChessDaily {
		
		Last last;
		Best best;
		Record record;
		Tournament tournament;
		
		public Last getLast() {
			return last;
		}
		public void setLast(Last last) {
			this.last = last;
		}
		public Best getBest() {
			return best;
		}
		public void setBest(Best best) {
			this.best = best;
		}
		public Record getRecord() {
			return record;
		}
		public void setRecord(Record record) {
			this.record = record;
		}
		public Tournament getTournament() {
			return tournament;
		}
		public void setTournament(Tournament tournament) {
			this.tournament = tournament;
		}
		
	}

	public class Chess960Daily {
		Last last;
		Best best;
		Record record;
		Tournament tournament;
		
		public Last getLast() {
			return last;
		}
		public void setLast(Last last) {
			this.last = last;
		}
		public Best getBest() {
			return best;
		}
		public void setBest(Best best) {
			this.best = best;
		}
		public Record getRecord() {
			return record;
		}
		public void setRecord(Record record) {
			this.record = record;
		}
		public Tournament getTournament() {
			return tournament;
		}
		public void setTournament(Tournament tournament) {
			this.tournament = tournament;
		}
	}

	public class ChessRapid {
		Last last;
		Best best;
		Record record;
		
		public Last getLast() {
			return last;
		}
		public void setLast(Last last) {
			this.last = last;
		}
		public Best getBest() {
			return best;
		}
		public void setBest(Best best) {
			this.best = best;
		}
		public Record getRecord() {
			return record;
		}
		public void setRecord(Record record) {
			this.record = record;
		}

	}

	public class ChessBullet {
		Last last;
		Best best;
		Record record;
		
		public Last getLast() {
			return last;
		}
		public void setLast(Last last) {
			this.last = last;
		}
		public Best getBest() {
			return best;
		}
		public void setBest(Best best) {
			this.best = best;
		}
		public Record getRecord() {
			return record;
		}
		public void setRecord(Record record) {
			this.record = record;
		}
	}

	public class ChessBlitz {

		Last last;
		Best best;
		Record record;
		
		public Last getLast() {
			return last;
		}
		public void setLast(Last last) {
			this.last = last;
		}
		public Best getBest() {
			return best;
		}
		public void setBest(Best best) {
			this.best = best;
		}
		public Record getRecord() {
			return record;
		}
		public void setRecord(Record record) {
			this.record = record;
		}
	}

	public class  Tactics{
		Highest highest;
		Lowest lowest;
		public Highest getHighest() {
			return highest;
		}
		public void setHighest(Highest highest) {
			this.highest = highest;
		}
		public Lowest getLowest() {
			return lowest;
		}
		public void setLowest(Lowest lowest) {
			this.lowest = lowest;
		}
	}
	
	public class Lessons{
		
		Highest highest;
		Lowest lowest;
		public Highest getHighest() {
			return highest;
		}
		public void setHighest(Highest highest) {
			this.highest = highest;
		}
		public Lowest getLowest() {
			return lowest;
		}
		public void setLowest(Lowest lowest) {
			this.lowest = lowest;
		}
	}
	
	public class Highest {
		int rating;
		long date;
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		
	}
	
	public class Lowest {
		int rating;
		long date;
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
	}
	
	public class Last {
		int rating;
		long date;
		int rd;
		
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		public int getRd() {
			return rd;
		}
		public void setRd(int rd) {
			this.rd = rd;
		}
	}
	public class Best {
		int rating;
		long date;
		String game;
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		public String getGame() {
			return game;
		}
		public void setGame(String game) {
			this.game = game;
		}
	}
	
	public class Record {
		int win;
		int loss;
		int draw;
		int time_per_move;
		int timeout_percent;
		
		public int getWin() {
			return win;
		}
		public void setWin(int win) {
			this.win = win;
		}
		public int getLoss() {
			return loss;
		}
		public void setLoss(int loss) {
			this.loss = loss;
		}
		public int getDraw() {
			return draw;
		}
		public void setDraw(int draw) {
			this.draw = draw;
		}
		public int getTime_per_move() {
			return time_per_move;
		}
		public void setTime_per_move(int time_per_move) {
			this.time_per_move = time_per_move;
		}
		public int getTime_percent() {
			return timeout_percent;
		}
		public void setTime_percent(int timeout_percent) {
			this.timeout_percent = timeout_percent;
		}
	}
	public class Tournament {
		int points;
		int withdraw;
		int count;
		int highest_finish;
		public int getPoints() {
			return points;
		}
		public void setPoints(int points) {
			this.points = points;
		}
		public int getWithdraw() {
			return withdraw;
		}
		public void setWithdraw(int withdraw) {
			this.withdraw = withdraw;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getHighest_finish() {
			return highest_finish;
		}
		public void setHighest_finish(int highest_finish) {
			this.highest_finish = highest_finish;
		}
	}
	
	public ChessDaily getChess_daily() {
		return chess_daily;
	}
	public void setChess_daily(ChessDaily chess_daily) {
		this.chess_daily = chess_daily;
	}
	public Chess960Daily getChess960_daily() {
		return chess960_daily;
	}
	public void setChess960_daily(Chess960Daily chess960_daily) {
		this.chess960_daily = chess960_daily;
	}
	public ChessRapid getChess_rapid() {
		return chess_rapid;
	}
	public void setChess_rapid(ChessRapid chess_rapid) {
		this.chess_rapid = chess_rapid;
	}
	public ChessBullet getChess_bullet() {
		return chess_bullet;
	}
	public void setChess_bullet(ChessBullet chess_bullet) {
		this.chess_bullet = chess_bullet;
	}
	public ChessBlitz getChess_blitz() {
		return chess_blitz;
	}
	public void setChess_blitz(ChessBlitz chess_blitz) {
		this.chess_blitz = chess_blitz;
	}
	public Tactics getTactics() {
		return tactics;
	}
	public void setTactics(Tactics tactics) {
		this.tactics = tactics;
	}
	public Lessons getLessons() {
		return lessons;
	}
	public void setLessons(Lessons lessons) {
		this.lessons = lessons;
	}
}




