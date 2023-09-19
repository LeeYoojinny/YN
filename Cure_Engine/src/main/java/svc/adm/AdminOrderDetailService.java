package svc.adm;

import vo.Payment;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CarDAO;
import dao.OrderDAO;
import vo.Car;
import vo.Order;

public class AdminOrderDetailService {

	public Order getOrderDetail(String ordernum) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		Order orderInfo = orderDAO.getOrderDetail(ordernum);
		
		close(con);
		return orderInfo;
	}

	public Car getCarDetail(String car_id) {
		Connection con = getConnection();
		CarDAO carDAO = CarDAO.getInstance();
		carDAO.setConnection(con);
		
		Car carDetail = carDAO.selectDetail(car_id);
		
		close(con);
		return carDetail;
	}

	public Payment getPayDetail(String ordernum) {
		Connection con = getConnection();
		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.setConnection(con);
		
		Payment payInfo = orderDAO.getPayDetail(ordernum);
		
		close(con);
		return payInfo;
	}

}
