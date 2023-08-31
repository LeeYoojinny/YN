package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Car;

public class CarDAO {
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		private CarDAO() {}
		
		private static CarDAO carDAO;
		
		public static CarDAO getInstance() {
			if(carDAO == null) {//DogDAO 객체가 없으면
				carDAO = new CarDAO();
			}
			return carDAO; //기존 DogDAO객체가 있으면 주소를 리턴
		}
		
		
		/**
		 * 1.DB연결(Service 통해) → 2.sql 실행 → 3. 결과처리 → 4. 연결해제
		 * 
		 */
		
		//DB연결(Service)
		public void setConnection (Connection con){
			this.con = con;
		}

		/*---- 차량 정보 가져오기 -------------------------------------------------------------------------------------------*/
		public Car selectCarInfo(String inputCarId) {
			Car carInfo = null;
			
			String sql = "select * from tbl_car where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, inputCarId);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					carInfo = new Car();
					carInfo.setCar_id(rs.getString("car_id"));
					carInfo.setDealer_id(rs.getString("dealer_id"));
				}
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectCarInfo()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return carInfo;
		}

		
		/*---- 차량 등록하기 -------------------------------------------------------------------------------------------*/
		public int insertCar(Car car) {
			int insertResult = 0;
			
			String sql = "insert into tbl_car(dealer_id,car_id,car_brand,car_name,car_color,car_price,"
					+ "car_capacity,car_fuel,car_transmission,car_type,car_accident,car_year,car_distance,"
					+ "car_image1,car_image2,car_content,car_accident_detail) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car.getDealer_id());
				pstmt.setString(2, car.getCar_id());
				pstmt.setString(3, car.getCar_brand());
				pstmt.setString(4, car.getCar_name());
				pstmt.setString(5, car.getCar_color());
				pstmt.setInt(6, car.getCar_price());
				pstmt.setInt(7, car.getCar_capacity());
				pstmt.setString(8, car.getCar_fuel());
				pstmt.setString(9, car.getCar_transmission());
				pstmt.setString(10, car.getCar_type());
				pstmt.setString(11, car.getCar_accident());
				pstmt.setInt(12, car.getCar_year());
				pstmt.setInt(13, car.getCar_distance());
				pstmt.setString(14, car.getCar_image1());
				pstmt.setString(15, car.getCar_image2());
				pstmt.setString(16, car.getCar_content());
				pstmt.setString(17, car.getCar_accident_detail());
				
				insertResult = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 insertCar()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return insertResult;
		}

		/*---- '등록된 차량 뷰' 보기용 ------------------------------------------------------------------------------*/
		public ArrayList<Car> selectAllCarInfo() {
			ArrayList<Car> allCarList = null;
			
			String sql = "select * from tbl_car";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					allCarList = new ArrayList<Car>();
					do {
						allCarList.add(new Car(
								rs.getString("dealer_id"),
								rs.getString("car_id"),
								rs.getString("car_brand"),
								rs.getString("car_name"),
								rs.getInt("car_price"),
								rs.getInt("car_year"),
								rs.getString("car_image1"),
								rs.getInt("car_like"),
								rs.getString("sale_YN")
								));
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectAllCarInfo()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return allCarList;
		}

		
		
		
}
