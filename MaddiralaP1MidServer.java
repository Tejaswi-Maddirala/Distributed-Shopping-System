import java.io.*;
import java.util.Scanner;
import java.net.*;
import java.lang.Thread;
public class MaddiralaP1MidServer{
public static Scanner x;
public static String group = null, pointsString = null;

public static void main(String args[]) throws Exception {
String clientUsername, clientPassword, acknowledgement = null, itemList = null;
ServerSocket receiver = new ServerSocket(16110);
Socket connSocket = receiver.accept();
System.out.println("Client connected");
BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());
clientUsername = inFromClient.readLine();
clientPassword = inFromClient.readLine();
System.out.println("From user: " + clientUsername);
System.out.println("From user: " + clientPassword);

if((clientUsername != null) && (clientPassword != null))
{
acknowledgement = "Acknowledged\n";
outToClient.writeBytes(acknowledgement);
}
String userListPath = "userList.txt";
verifyLogin(clientUsername, clientPassword, userListPath,outToClient);

// Middle Server acts as a client
Thread.sleep(5000);

Socket clientSocket1 =new Socket("192.168.0.5", 16111);

if(group.equals("Platinum")){ System.out.println("Connecting to Platinum Server......"); }
else if(group.equals("Gold")){ System.out.println("Connecting to Gold Server......"); }
else { System.out.println("Connecting to Silver Server......"); }
BufferedReader fromServer= new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));

itemList = fromServer.readLine();
System.out.println("Please select the items/options that you want to buy from below list");
System.out.println(itemList);

BufferedReader option = new BufferedReader(new InputStreamReader(System.in));
String itemNumber = option.readLine();

DataOutputStream toServer= new DataOutputStream(clientSocket1.getOutputStream());
DataOutputStream toServer1=new DataOutputStream(clientSocket1.getOutputStream());
String str = "pointsString\n";
toServer.writeBytes(itemNumber);
toServer1.writeBytes(str);
clientSocket1.close();
}


public static void verifyLogin(String username, String pwd, String filepath, DataOutputStream outToClient) {
boolean found = false;
String tempU = "";
String tempP = "", valid = "";
try
{
x = new Scanner(new File(filepath));
x.useDelimiter("[,\n]");
while(x.hasNext() && !found)
{
tempU = x.next();
tempP = x.next();

if(tempU.trim().equals(username.trim()) && tempP.trim().equals(pwd.trim()))
{
found = true;
}
if(found){
group = x.next();
valid = "Verified Credentials. Success, logging in!\n";
pointsString = x.next();
}
else{
valid = "Invalid Credentials\n";
}
}
x.close();
outToClient.writeBytes(valid);
}
catch(Exception e) {
	System.out.println("Error");
}
}
}
