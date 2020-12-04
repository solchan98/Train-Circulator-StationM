

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TrainAcyclic extends Train{
	//생성자 메서드.
	public TrainAcyclic(int line, String dir) {
		super(line, dir);
		try {
			buildRoute();
			buildName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setRouteDir(dir);
		this.setCurStation(dir);
		this.rem_next_time = route[cur_station].get(0).getTime();//초기 설정.
		next_station = route[cur_station].get(0).getEnd();
		
	}
	//경로 방향 변경 메서드
		public void changeRoute() {
			if(dir.equals("상행")) {
				this.route = route_down;
				this.dir = "하행";
				cur_station = route_cnt;
				rem_next_time = route[cur_station].get(0).getTime();
				next_station = route[cur_station].get(0).getEnd();
			}
			else {
				this.route = route_up;
				this.dir = "상행";
				cur_station = 1;
				rem_next_time = route[cur_station].get(0).getTime();
				next_station = route[cur_station].get(0).getEnd();
			}
		}
	//다음역까지 남은 시간 반환 메서드.
	public void setRemNextTime() {
		if(rem_next_time <= 0) {
			if(this.dir.equals("하행") && cur_station == 2)
				this.changeRoute();
			else if(this.dir.equals("상행") && cur_station == route_cnt - 1)
				this.changeRoute();
			else {
				cur_station = route[cur_station].get(0).getEnd();
				rem_next_time = route[cur_station].get(0).getTime();
				next_station = route[cur_station].get(0).getEnd();
			}
		}
		else
			rem_next_time -= 1;//1초씩 감소.
	}
	//경로 설정 메서드.
	@Override
	public void buildRoute() throws IOException {
		String temp_s;
		InputStream	inputStream = null;
		if(line == 2 || line == 3 || line == 4 || line == 5 || line == 7 || line == 8 || line == 9)
			inputStream = new FileInputStream("/Users/sol/google/2-2학기/팀프로젝트1/TrainCirculator/metrodata/indexL/line" + line + ".txt");
		else
			System.out.println("잘못된 파일이 연결되었습니다.");
		InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader trainRoute = new BufferedReader(inputreader);
		while ((temp_s = trainRoute.readLine()) != null) // Subway.txt의 라인 수 만큼 temp_n증가.
			route_cnt++;
		route_cnt++;
        route_up = new ArrayList[route_cnt + 2];
        route_down = new ArrayList[route_cnt + 2];
        for (int i = 1; i <= route_cnt; i++) {
            route_up[i] = new ArrayList<>();//경로의 수 만큼 생성, [0]번은 빈역
            route_down[i] = new ArrayList<>();//경로의 수 만큼 생성, [0]번은 빈역
        }
        // 데이터 입력 단계.
        if(line == 2 || line == 3 || line == 4 || line == 5 || line == 7 || line == 8 || line == 9)
			inputStream = new FileInputStream("/Users/sol/google/2-2학기/팀프로젝트1/TrainCirculator/metrodata/indexL/line" + line + ".txt");
		else
			System.out.println("잘못된 파일이 연결되었습니다.");
        inputreader = new InputStreamReader(inputStream);
        trainRoute = new BufferedReader(inputreader);
		while ((temp_s = trainRoute.readLine()) != null) {
		    String[] split = temp_s.split("	");// tap으로 구분.
		    int start_t = Integer.parseInt(split[0]);
		    int end_t = Integer.parseInt(split[1]);
		    int time_t = Integer.parseInt(split[2]);
		    route_up[start_t].add(new Route(end_t, "상행", time_t));// 경로(라인) 양방향이기 때문에,
		    route_down[end_t].add(new Route(start_t, "하행", time_t)); // 양방향으로 데이터 값 설정.
		}
    }
}
