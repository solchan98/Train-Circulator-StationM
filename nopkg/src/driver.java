

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;


public class driver {
	static Train[] train = new Train[18];

	public static void main(String[] args) throws SQLException {
		driver metro = new driver();
		DBManager db = new DBManager();
		Connection con = db.makeDB();
		Statement stmt = con.createStatement();
		String sql = "UPDATE MJ.metro SET cur_station ='301' WHERE line = '3Up'";
		stmt.executeUpdate(sql);
		
		

		//metro.makeTrain();
		//metro.runrun();
		
		
	}
	//열차 운행 메서드.
	public void runrun() {
		Timer timer = new Timer();//타이머 변수. 
		TimerTask task = new TimerTask() {//주기 반복 실행.

			@Override
			public void run() {
				for(int i = 0; i < train.length; i++) {
					train[i].run();
				}
				System.out.println("---------------------------------------");
			}	
		};
		timer.schedule(task, 0, 3000);	
	}
	//열차 생성 메서드.
	public void makeTrain() {
		train[0] = new TrainCycle(1, "상행");
		train[1] = new TrainCycle(1, "하행");
		train[2] = new TrainAcyclic(2, "상행");
		train[3] = new TrainAcyclic(2, "하행");
		train[4] = new TrainAcyclic(3, "상행");
		train[5] = new TrainAcyclic(3, "하행");
		train[6] = new TrainAcyclic(4, "상행");
		train[7] = new TrainAcyclic(4, "하행");
		train[8] = new TrainAcyclic(5, "상행");
		train[9] = new TrainAcyclic(5, "하행");
		train[10] = new TrainCycle(6, "상행");
		train[11] = new TrainCycle(6, "하행");
		train[12] = new TrainAcyclic(7, "상행");
		train[13] = new TrainAcyclic(7, "하행");
		train[14] = new TrainAcyclic(8, "상행");
		train[15] = new TrainAcyclic(8, "하행");
		train[16] = new TrainAcyclic(9, "상행");
		train[17] = new TrainAcyclic(9, "하행");
	}
}