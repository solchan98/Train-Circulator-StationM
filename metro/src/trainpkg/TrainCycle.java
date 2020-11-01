package trainpkg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TrainCycle extends train {
	//생성자 메서드.
	public TrainCycle(int line, String dir) {
		super(line, dir);
		try {
			buildRoute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setRouteDir(dir);
		this.setCurStation(dir);
		this.rem_next_time = route[cur_station].get(0).getTime();//초기 설정.
	}
	
	//다음역까지 남은 시간 설정 메서드.
	public void setRemNextTime() {
		if(rem_next_time <= 0) {
			cur_station = route[cur_station].get(0).getEnd();
			rem_next_time = route[cur_station].get(0).getTime();
		}
		else
			rem_next_time -= 3;//3초씩 감소.
	}
	/*남은 시간 구하는 메서드.
	@Override
	public int getTotalTime(int station) {
		tp_cur_sta = cur_station;//계산을 위한 현재 역을 실제 현재역으로 설정.
		int i = route[tp_cur_sta].get(0).getEnd();
		//System.out.println(i);
		while(tp_cur_sta != station) {
			rem_total_time += route[tp_cur_sta].get(0).getTime();
			System.out.println(rem_total_time / 60 + "분");
			tp_cur_sta = route[tp_cur_sta].get(0).getEnd();
		}
		return rem_total_time;
	}*/
	//경로 생성 메서드.
	@Override
	public void buildRoute() throws IOException {
		String temp_s;
		InputStream	inputStream = null;
		if(line == 1)
			inputStream = new FileInputStream("/Users/sol/google/2-2학기/팀프로젝트1/metro/metro/indexL/line1.txt");
		InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader trainRoute = new BufferedReader(inputreader);
		while ((temp_s = trainRoute.readLine()) != null) // Subway.txt의 라인 수 만큼 temp_n증가.
			route_cnt++;
        route_up = new ArrayList[route_cnt + 1];
        route_down = new ArrayList[route_cnt + 1];
        for (int i = 1; i <= route_cnt; i++) {
            route_up[i] = new ArrayList<>();//경로의 수 만큼 생성, [0]번은 빈역
            route_down[i] = new ArrayList<>();//경로의 수 만큼 생성, [0]번은 빈역
        }
        // 데이터 입력 단계.
        if(line == 1)
        	inputStream = new FileInputStream("/Users/sol/google/2-2학기/팀프로젝트1/metro/metro/indexL/line1.txt");
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
