package com.example.springbootrest.service;

import com.example.springbootrest.entity.Order;
import com.example.springbootrest.DAO.OrderRepository;
import com.example.springbootrest.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderDAO;
    @Autowired
    public OrderServiceImpl(OrderRepository theDataDAO) {
        orderDAO = theDataDAO;
    }
    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> findByUid(int uid) {
        return orderDAO.findByUidEquals(uid);
    }

    @Override
    public List<Order> findAllUnprocessedOrders(int status) {
        return orderDAO.findByStatusOrderByOid(status);
    }

    @Override
    public Order findByOid(int theId) {
        Optional<Order> result = orderDAO.findById(theId);

        Order theOrder = null;
        if(result.isPresent()) {
            theOrder = result.get();
        }else {
            throw new RuntimeException("Did not find order with id - " + theId);
        }

        return theOrder;
    }

    @Override
    public Order save(Order theOrder) {
        return orderDAO.save(theOrder);
    }

    @Override
    public void deleteByOid(int oid) {
        orderDAO.deleteById(oid);
    }

    @Override
    public void deleteAll() {
        orderDAO.deleteAll();
    }
}
