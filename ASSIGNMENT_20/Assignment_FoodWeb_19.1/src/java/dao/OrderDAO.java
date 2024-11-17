/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import dto.Food;
import dto.Order;
import dto.OrderItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import utils.DBUtill;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<Order> getAllOrders(int accid, int status) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [O_ID],[Account_ID],[CustomerName],[TotalPrice],[PhoneNumber],[OrderDate],[Payment],[Status] from [dbo].[tblORDER]\n"
                        + "where [Status] = ? and [Account_ID] = ?\n"
                        + "order by [OrderDate] desc";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, accid);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String phone = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, phone, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrdersByAccId(int accid) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [O_ID],[Account_ID],[CustomerName],[TotalPrice],[PhoneNumber],[OrderDate],[Payment],[Status] from [dbo].[tblORDER]\n"
                        + "where [Account_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, accid);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String phone = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, phone, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Order getOrder(int orderid) {

        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [O_ID],[Account_ID],[CustomerName],[TotalPrice],[PhoneNumber],[OrderDate],[Payment],[Status] from [dbo].[tblORDER]\n"
                        + " where [O_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int ordid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String phone = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(ordid, accId, customerName, totalPrice, phone, new java.util.Date(d.getTime()), payment, sta);
                        return ord;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<OrderItem> getItemfromOrder(int orderId) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select [Item_ID],[O_ID],[F_ID],[Quantity],[Price]\n"
                        + "from [dbo].[tblITEM] where [O_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderId);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int itemId = rs.getInt("Item_ID");
                        int orId = rs.getInt("O_ID");
                        int foodID = rs.getInt("F_ID");
                        int quantity = rs.getInt("Quantity");
                        int price = rs.getInt("Price");
                        OrderItem orItem = new OrderItem(itemId, orId, foodID, quantity, price);
                        list.add(orItem);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int saveOrder(Account acc, String payment, String fullName, String phone, HashMap<Food, Integer> cart) {
        int total = 0;
        for (Food i : cart.keySet()) {
            total = total + cart.get(i) * i.getPrice();
        }
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "insert into [dbo].[tblORDER]([Account_ID],[CustomerName],[TotalPrice],[PhoneNumber],[OrderDate],[Payment]) \n"
                        + "values(?,?,?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, acc.getAccid());
                pst.setString(2, fullName);
                pst.setInt(3, total);
                pst.setString(4, phone);
                pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                pst.setString(6, payment);
                result = pst.executeUpdate();
                //lấy orderId vừa chèn vào bảng order
                if (result > 0) {
                    sql = "select top 1 [O_ID] from [dbo].[tblORDER] \n"
                            + "order by [O_ID] desc";
                    pst = cn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int orderId = rs.getInt("O_ID");
                        //chèn các dòng từ cart vào orderItem
                        for (Food i : cart.keySet()) {
                            sql = "insert into [dbo].[tblITEM] values(?,?,?,?)";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, orderId);
                            pst.setInt(2, i.getId());
                            pst.setInt(3, cart.get(i));
                            pst.setInt(4, i.getPrice());
                            result = pst.executeUpdate();
                        }
                        cn.commit();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<Order> getAllOrdersByDateAndPhone(String phone, String date) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]\n"
                        + "WHERE [PhoneNumber] = ? AND CAST([OrderDate] AS DATE) = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, phone);
                pst.setString(2, date);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrdersByDatePhoneAccid(String phone, String date, int accid) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]\n"
                        + "WHERE [PhoneNumber] = ? AND CAST([OrderDate] AS DATE) = ? AND [Account_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, phone);
                pst.setString(2, date);
                pst.setInt(3, accid);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrdersByPhoneAccid(String phone, int accid) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]\n"
                        + "WHERE [PhoneNumber] = ? AND [Account_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, phone);
                pst.setInt(2, accid);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrdersByDateAccid(String date, int accid) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]\n"
                        + "WHERE CAST([OrderDate] AS DATE) = ? AND [Account_ID] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, date);
                pst.setInt(2, accid);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrdersByDate(String date) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]\n"
                        + "WHERE CAST([OrderDate] AS DATE) = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, date);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrdersByPhone(String phone) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]\n"
                        + "WHERE [PhoneNumber] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, phone);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER]";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public int updateStatus(int orderID, int status) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[tblORDER]\n"
                        + "set [Status]= ?\n"
                        + "where [O_ID]= ?";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, orderID);
                result = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int numberOrderInMonth(int month) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select count(*) from [dbo].[tblORDER] a\n"
                        + "where MONTH(a.OrderDate)= ? and a.Status = 4";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, month);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int numberMoneyInMonth(int month) {
        int result = 0;
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "select sum([TotalPrice]) from [dbo].[tblORDER] a\n"
                        + "where MONTH(a.OrderDate)= ? and a.Status = 4";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, month);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public ArrayList<Order> getAllOrdersByStatus(String status) {
        ArrayList list = new ArrayList<>();
        try {
            cn = DBUtill.makeConnection();
            if (cn != null) {
                String sql = "SELECT [O_ID], [Account_ID], [CustomerName], [TotalPrice], [PhoneNumber], [OrderDate], [Payment], [Status]\n"
                        + "FROM [dbo].[tblORDER] where [Status] = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, status);
                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("O_ID");
                        int accId = rs.getInt("Account_ID");
                        String customerName = rs.getString("CustomerName");
                        int totalPrice = rs.getInt("TotalPrice");
                        String ph = rs.getString("PhoneNumber");
                        Date d = rs.getDate("OrderDate");
                        String payment = rs.getString("Payment");
                        int sta = rs.getInt("Status");
                        Order ord = new Order(orderid, accId, customerName, totalPrice, ph, new java.util.Date(d.getTime()), payment, sta);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO ordao = new OrderDAO();
        System.out.println(ordao.getAllOrdersByStatus("2"));
    }
}
