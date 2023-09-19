package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Car;
import vo.User;
import vo.Wishlist;

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

		/*---- 차량 정보 중복체크용 -------------------------------------------------------------------------------------------*/
		public Car selectCarInfo(String inputCarId) {
			Car carInfo = null;
			
			String sql = "select * from tbl_car where car_delete='N' and car_id=?";
			
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
					+ "car_image1,car_image2,car_image3,car_image4,car_image5,car_content,car_accident_detail) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
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
				pstmt.setString(16, car.getCar_image3());
				pstmt.setString(17, car.getCar_image4());
				pstmt.setString(18, car.getCar_image5());
				pstmt.setString(19, car.getCar_content());
				pstmt.setString(20, car.getCar_accident_detail());
				
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
			
			String sql = "select * from tbl_car where car_delete='N' ORDER BY sale_YN DESC, car_id";
			
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
								rs.getString("sale_YN"),
								rs.getString("car_delete")
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

		/*---- '차량 검색 결과' 보기용 ------------------------------------------------------------------------------*/
		public ArrayList<Car> selectSearchCar(String car_brand, String car_color, String car_type, 
				int car_distance, int start_price, int end_price) {
			ArrayList<Car> searchCar = null;
			
			String[] car_brand_arr = car_brand.split(",");
			String where_brand = "";
			if(!car_brand.equals("X")) {
				for(int i=0; i<car_brand_arr.length; i++) {
					if(i == 0) {
						where_brand += " (car_brand='"+car_brand_arr[i]+"'";
					}else {
						where_brand += " or car_brand='"+car_brand_arr[i]+"'";
					}
				}
				where_brand += ")";
			}
			
			
			
			String[] car_color_arr = car_color.split(",");
			String where_color = "";
			if(!car_color.equals("X")) {
				for(int i=0; i<car_color_arr.length; i++) {
					if(i == 0) {
						where_color += " (car_color='"+car_color_arr[i]+"'";
					}else {
						where_color += " or car_color='"+car_color_arr[i]+"'";
					}
				}
				where_color += ")";
			}
			
			String[] car_type_arr = car_type.split(",");
			String where_type = "";
			if(!car_type.equals("X")) {
				for(int i=0; i<car_type_arr.length; i++) {
					if(i == 0) {
						where_type += " (car_type like '%"+car_type_arr[i]+"%'";
					}else {
						where_type += " or car_type like '%"+car_type_arr[i]+"%'";
					}	
				}
				where_type += ")";
			}
			
			System.out.println("car_brand dao에서 만들어진 쿼리문 : " +where_brand);
			System.out.println("car_color dao에서 만들어진 쿼리문 : " +where_color);
			System.out.println("car_type dao에서 만들어진 쿼리문 : " +where_type);
					
			
			String where = ""; // 최종 조건절 문자열

			if (!where_brand.isEmpty()) {
			    where += where_brand;
			}

			if (!where_color.isEmpty()) {
			    if (!where.isEmpty()) {
			        where += " and ";
			    }
			    where += where_color;
			}

			if (!where_type.isEmpty()) {
			    if (!where.isEmpty()) {
			        where += " and ";
			    }
			    where += where_type;
			}
			
			if(car_distance > 0) {
				if (!where.isEmpty()) {
			        where += " and";
			    }
				where += " car_distance <="+car_distance;
			}
			
			if(start_price > 0) {
				if (!where.isEmpty()) {
					where += " and";
				}
				where += " (car_price >="+start_price+")";
			}
			
			if(end_price > 0) {
				if (!where.isEmpty()) {
					where += " and";
				}
				where += " (car_price<="+end_price+")";
			}
			
			String sql = "select * from tbl_car where car_delete='N' and"+where;
			
			System.out.println("만들어진 sql문 : "+sql);
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					searchCar = new ArrayList<Car>();
					do {
						searchCar.add(new Car(
								rs.getString("dealer_id"),
								rs.getString("car_id"),
								rs.getString("car_brand"),
								rs.getString("car_name"),
								rs.getInt("car_price"),
								rs.getInt("car_year"),
								rs.getString("car_image1"),
								rs.getInt("car_like"),
								rs.getString("sale_YN"),
								rs.getString("car_delete")
								));
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectSearchCar()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}			
			return searchCar;
		}

		

		/*---- 관심상품 등록 후 like 숫자 올리기 ------------------------------------------------------------------------------*/
		public int likeUpdate(String car_id) {
			int likeUpResult = 0;
			
			String sql = "update tbl_car set car_like=(car_like+1) where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,car_id);
				
				likeUpResult = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 likeUpdate()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return likeUpResult;
		}
		
		/*---- 관심상품 등록 후 like 숫자 내리기 ------------------------------------------------------------------------------*/
		public int likeDown(String car_id) {
			int likeDownResult = 0;
			
			String sql = "update tbl_car set car_like=(car_like-1) where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,car_id);
				
				likeDownResult = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 likeDown()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return likeDownResult;
		}

		
		
		
		/*---- 관심상품보기 -------------------------------------------------------------------*/
		public ArrayList<Car> selectmyWishCar(String[] all_car_id, int page, int limit) {
			ArrayList<Car> wishCar = new ArrayList<Car>();
			
			System.out.println("page값 : "+page);
			
			String sql = "SELECT * FROM tbl_car WHERE car_delete = 'N' AND car_id IN (";
		    for (int i = 0; i < all_car_id.length; i++) {
		        sql += "?";
		        if (i < all_car_id.length - 1) {
		            sql += ",";
		        }
		    }
		    sql += ") LIMIT ?, 5";

		    int startrow = (page - 1) * 5;

		    try {
		        pstmt = con.prepareStatement(sql);

		        // car_id 파라미터 설정
		        for (int i = 0; i < all_car_id.length; i++) {
		            pstmt.setString(i + 1, all_car_id[i]);
		        }

		        // 페이지 파라미터 설정
		        pstmt.setInt(all_car_id.length + 1, startrow);

		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            wishCar.add(new Car(
		                    rs.getString("dealer_id"),
		                    rs.getString("car_id"),
		                    rs.getString("car_brand"),
		                    rs.getString("car_name"),
		                    rs.getInt("car_price"),
		                    rs.getInt("car_year"),
		                    rs.getString("car_image1"),
		                    rs.getInt("car_like"),
		                    rs.getString("sale_YN"),
		                    rs.getString("car_delete")
		            ));
		        }
				
				for(Car car_brand : wishCar) {
					System.out.println("wishCar 안의 브랜드 확인 : "+car_brand.getCar_brand());
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectmyWishCar()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
						
			return wishCar;
		}

		/*---- 차량상세정보 -------------------------------------------------------------------*/
		public Car selectDetail(String car_id) {
			Car detail = null;
			
			String sql = "select * from tbl_car where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car_id);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					detail = new Car();
					detail.setDealer_id(rs.getString("dealer_id"));
					detail.setCar_id(rs.getString("car_id"));
					detail.setCar_brand(rs.getString("car_brand"));
					detail.setCar_name(rs.getString("car_name"));
					detail.setCar_color(rs.getString("car_color"));
					detail.setCar_price(rs.getInt("car_price"));
					detail.setCar_capacity(rs.getInt("car_capacity"));
					detail.setCar_fuel(rs.getString("car_fuel"));
					detail.setCar_transmission(rs.getString("car_transmission"));
					detail.setCar_type(rs.getString("car_type"));
					detail.setCar_accident(rs.getString("car_accident"));
					detail.setCar_year(rs.getInt("car_year"));
					detail.setCar_distance(rs.getInt("car_distance"));
					detail.setCar_image1(rs.getString("car_image1"));
					detail.setCar_image2(rs.getString("car_image2"));
					detail.setCar_image3(rs.getString("car_image3"));
					detail.setCar_image4(rs.getString("car_image4"));
					detail.setCar_image5(rs.getString("car_image5"));
					detail.setCar_content(rs.getString("car_content"));
					detail.setCar_accident_detail(rs.getString("car_accident_detail"));
					detail.setCar_like(rs.getInt("car_like"));
					detail.setSale_YN(rs.getString("sale_YN"));
				}
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectCarInfo()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return detail;
		}

		/*---관심상품을 1개이상 삭제할때 좋아요 숫자 다운 -------------------------------------------------------------------*/
		public int likeManyDown(String[] car_ids) {
			int result = 0;
			
			String where_car_id = "";
			for(int i=0; i<car_ids.length; i++) {
				if(i == 0) {
					where_car_id += " (car_id='"+car_ids[i]+"'";
				}else {
					where_car_id += " or car_id='"+car_ids[i]+"'";
				}
			}
			where_car_id += ")";
			String sql = "update tbl_car set car_like=(car_like-1) where"+where_car_id;
			try {
				pstmt = con.prepareStatement(sql);
				result = pstmt.executeUpdate();

			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 removeWish()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return result;
		}
		
		/*--- 페이징처리 위해 나의 판매차량 개수 가져오기 -------------------------------------------------------------------*/
		public int getSaleCount(String user_id) {
			int count = 0;
			
			String sql = "select count(*) from tbl_car where car_delete='N' and dealer_id=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 getSaleCount()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return count;
		}
		
		/*---- '나의판매차량' 보기용 ------------------------------------------------------------------------------*/
		public ArrayList<Car> selectmySaleCar(String user_id, int page, int limit) {
			ArrayList<Car> mySaleCar = null;
			
			String sql = "select * from tbl_car where car_delete='N' and dealer_id=? ORDER BY sale_YN DESC, car_id limit ?,5";
			int startrow = (page - 1) * 5;
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,user_id);
				pstmt.setInt(2, startrow);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					mySaleCar = new ArrayList<Car>();
					do {
						mySaleCar.add(new Car(
								rs.getString("dealer_id"),
								rs.getString("car_id"),
								rs.getString("car_brand"),
								rs.getString("car_name"),
								rs.getInt("car_price"),
								rs.getInt("car_year"),
								rs.getString("car_image1"),
								rs.getInt("car_like"),
								rs.getString("sale_YN"),
								rs.getString("car_delete")
								));
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectmySaleCar()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return mySaleCar;
		}
		
		/*--- 딜러가 자신이 등록한 차량 삭제 -------------------------------------------------------------------*/
		public int removeMyCar(String[] car_ids, String user_id) {
			int result = 0;
			
			String where_car_id = "";
			for(int i=0; i<car_ids.length; i++) {
				if(i == 0) {
					where_car_id += " (car_id='"+car_ids[i]+"'";
				}else {
					where_car_id += " or car_id='"+car_ids[i]+"'";
				}
			}
			where_car_id += ")";
			
			String sql = "update tbl_car set car_delete='Y' where dealer_id=? and"+where_car_id;
			System.out.println("만들어진 sql문 : "+sql);
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				result = pstmt.executeUpdate();

			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 removeMyCar()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return result;
		}
		
		/*--- 등록된 차량 수정위해 정보가져오기 -------------------------------------------------------------------*/
		public Car getCarInfo(String car_id) {
			Car carInfo = null;
			
			String sql = "select * from tbl_car where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car_id);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					carInfo = new Car();
					carInfo.setDealer_id(rs.getString("dealer_id"));
					carInfo.setCar_id(rs.getString("car_id"));
					carInfo.setCar_brand(rs.getString("car_brand"));
					carInfo.setCar_name(rs.getString("car_name"));
					carInfo.setCar_color(rs.getString("car_color"));
					carInfo.setCar_price(rs.getInt("car_price"));
					carInfo.setCar_capacity(rs.getInt("car_capacity"));
					carInfo.setCar_fuel(rs.getString("car_fuel"));
					carInfo.setCar_transmission(rs.getString("car_transmission"));
					carInfo.setCar_type(rs.getString("car_type"));
					carInfo.setCar_accident(rs.getString("car_accident"));
					carInfo.setCar_year(rs.getInt("car_year"));
					carInfo.setCar_distance(rs.getInt("car_distance"));
					carInfo.setCar_image1(rs.getString("car_image1"));
					carInfo.setCar_image2(rs.getString("car_image2"));
					carInfo.setCar_image3(rs.getString("car_image3"));
					carInfo.setCar_image4(rs.getString("car_image4"));
					carInfo.setCar_image5(rs.getString("car_image5"));
					carInfo.setCar_content(rs.getString("car_content"));
					carInfo.setCar_accident_detail(rs.getString("car_accident_detail"));
					carInfo.setCar_like(rs.getInt("car_like"));
					carInfo.setSale_YN(rs.getString("sale_YN"));
				}
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 getCarInfo()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			return carInfo;
		}

		/*--- 등록된 차량 수정하기 -------------------------------------------------------------------*/
		public int updateCar(Car car) {
			int updateResult = 0;
			
			String sql = "update tbl_car set car_brand=?, car_name=?, car_color=?, car_price=?, car_capacity=?, "
					+ "car_fuel=?, car_transmission=?, car_type=?, car_accident=?, car_year=?, car_distance=?, "
					+ "car_image1=?, car_image2=?, car_image3=?, car_image4=?, car_image5=?, car_content=?, "
					+ "car_accident_detail=? where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car.getCar_brand());
				pstmt.setString(2, car.getCar_name());
				pstmt.setString(3, car.getCar_color());
				pstmt.setInt(4, car.getCar_price());
				pstmt.setInt(5, car.getCar_capacity());
				pstmt.setString(6, car.getCar_fuel());
				pstmt.setString(7, car.getCar_transmission());
				pstmt.setString(8, car.getCar_type());
				pstmt.setString(9, car.getCar_accident());
				pstmt.setInt(10, car.getCar_year());
				pstmt.setInt(11, car.getCar_distance());
				pstmt.setString(12, car.getCar_image1());
				pstmt.setString(13, car.getCar_image2());
				pstmt.setString(14, car.getCar_image3());
				pstmt.setString(15, car.getCar_image4());
				pstmt.setString(16, car.getCar_image5());
				pstmt.setString(17, car.getCar_content());
				pstmt.setString(18, car.getCar_accident_detail());
				pstmt.setString(19, car.getCar_id());
				
				updateResult = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 updateCar()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return updateResult;
		}


		public String getDealer_id(String car_id) {
			String dealer_id = "";
			
			String sql = "select dealer_id from tbl_car where car_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,car_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dealer_id = rs.getString(1);
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 getDealer_id()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			return dealer_id;
		}


		public ArrayList<Car> brandInfo(String car_brand) {
			ArrayList<Car> carInfo = null;
			
			String sql = "select * from tbl_car where sale_YN='Y' and car_brand=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, car_brand);

				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					carInfo = new ArrayList<Car>();
					do {
						carInfo.add(new Car(
								rs.getString("dealer_id"),
								rs.getString("car_id"),
								rs.getString("car_brand"),
								rs.getString("car_name"),
								rs.getInt("car_price"),
								rs.getInt("car_year"),
								rs.getString("car_image1"),
								rs.getInt("car_like"),
								rs.getString("sale_YN"),
								rs.getString("car_delete")
								));
					}while(rs.next());
				}
				System.out.println(car_brand+"브랜드의 차량 수 : "+(carInfo.size()+1));
					
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 brandInfo()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return carInfo;
		}

		/*--- 페이징처리 위해 모든 판매차량 개수 가져오기 -------------------------------------------------------------------*/
		public int getAllSaleCount() {
			int count = 0;
			
			String sql = "select count(*) from tbl_car where car_delete='N'";
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 getAllSaleCount()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return count;
		}

		/*--- 페이징처리 된 모든 판매차량 가져오기 -------------------------------------------------------------------*/
		public ArrayList<Car> selectAllSaleCar(int page, int limit) {
			ArrayList<Car> allCarList = null;
			
			String sql = "select * from tbl_car where car_delete='N' limit ?,5";
			int startrow = (page - 1) * 5;
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startrow);
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
								rs.getString("sale_YN"),
								rs.getString("car_delete")
								));
					}while(rs.next());
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 selectAllSaleCar()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return allCarList;
		}

		/*--- 딜러 탈퇴하면서 딜러의 자동차 삭제 -------------------------------------------------------------------*/
		public int deleteCar(String user_id) {
			int result = 0;
			
			String sql = "update tbl_car set car_delete='Y' where dealer_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,user_id);
				
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 deleteCar()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return result;
		}
		
		/*--- 딜러 재가입하면서 딜러의 자동차 다시 돌려놓기 -------------------------------------------------------------------*/
		public int getMyCarCount(String user_id) {
			int count = 0;
			
			String sql = "select count(*) from tbl_car where dealer_id=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 getMyCarCount()에서 발생한 에러 : "+e);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return count;
		}
				
		public int reSaleCar(String user_id) {
			int result = 0;
			
			String sql = "update tbl_car set car_delete='N' where dealer_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,user_id);
				
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 reSaleCar()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			return result;
		}

		
		
		/*--- 고객 주문 시 sale_YN 'W'로 변경 -------------------------------------------------------------------*/
		public int saleChangeW(String car_id) {
			int result = 0;
			
			String sql = "update tbl_car set sale_YN='W' where car_id=?"; 
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,car_id);
				
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				System.out.println("CarDAO 클래스의 saleChange()에서 발생한 에러 : "+e);
			}finally {
				close(pstmt);
			}
			
			return result;
		}


		
		
		
		
}
