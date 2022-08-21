package studentdb;

import java.sql.*;


import java.util.Scanner;



class myException extends Exception{
	myException(String myException){
		System.out.println(myException);
	}	
}
public class studentdb {
    Connection con = null;
    static Scanner sc = new Scanner(System.in);
    public Connection getConnect1(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://172.16.34.105/1rv21mc067","1rv21mc067","1rv21mc067");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    public int insert(){
        int i = 0;
        try{
            con = getConnect1();
            String usn, name, address;
            System.out.println("enter usn:");
            usn = sc.next();
            System.out.println("enter name:");
            name = sc.next();
            System.out.println("enter address");
            address= sc.next();
            String query = "insert into student values('"+usn+"','"+name+"','"+address+"')";
            PreparedStatement insertion = con.prepareStatement(query);
            i = insertion.executeUpdate(query);
            con.close();


        }
        catch(Exception e){
            System.out.println(e);
        }
        return i;
    }
	public void display(String usn) {
		try {
			con = getConnect1();
		String query = "select * from student where usn=?";
		if(usn.equalsIgnoreCase("1")) {
			throw new myException("usn is empty");
		}
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1,usn);
		ResultSet rs = ps.executeQuery();
	
		while(rs.next()) {
			System.out.println("usn "+rs.getString(1)+" name "+rs.getString(3)+
					" address "+rs.getString(3));
		}
		} catch (SQLException | ArithmeticException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
    public int delete(){
        int i = 0;
        try{
            con = getConnect1();
            
            System.out.println("enter usn:");
            String usn = sc.next();
            String query = "delete from student where usn = '"+usn+"'";
            PreparedStatement st = con.prepareStatement(query);
            i = st.executeUpdate(query);
            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
        return i;
    }
    public int update(){
        int i = 0;
        try{
            con = getConnect1();
            System.out.println("1:name 2:address");
            int n = sc.nextInt();
            if(n==1){
                String usn, name;
                System.out.println("enter usn:");
                usn = sc.next();
                System.out.println("enter name:");
                name = sc.next();
                String query = "update student set name = '"+name+"'where usn = '"+usn+"'";
                PreparedStatement st = con.prepareStatement(query);
                i = st.executeUpdate(query);

            }
            else if(n == 2){
                System.out.println("enter usn:");
                String usn = sc.next();
                System.out.println("enter address");
                String address = sc.next();
                String query = "update student set name = '"+address+"'where usn = '"+usn+"'";
                PreparedStatement st = con.prepareStatement(query);
                i=st.executeUpdate(query);
            }

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return i;

    }
    public static void main(String[] args){
        while(true){
            studentdb obj = new studentdb();
            int i = 0;
            System.out.println("1:insert 2:update 3:delete 4:display");
            int n = sc.nextInt();
            if(n == 1)
                i = obj.insert();
            else if(n == 2)
                i = obj.update();
            else if(n == 3)
                i = obj.delete();
            else if(n == 4) {
            	System.out.println("please enter student usn to display details:");
            	String usn1=sc.next();
            	
            	 obj.display(usn1);
            }
               
            if(i == 1)
                System.out.println("success");
            else if(n==4){
                System.out.println("Student Table contents");
            }
            else
                System.out.println("failed");
            System.out.println("do you want to continue? 1 for yes");
            n = sc.nextInt();
            if(n==1)
                continue;
            else
                break;
        }
    }

}