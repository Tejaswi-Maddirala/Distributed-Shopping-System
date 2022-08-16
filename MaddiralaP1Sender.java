import java.io.*;
import java.net.*;
public class MaddiralaP1Sender{
public static void main(String args[]) throws Exception{
String user, pwd, ack= null;
String hostname = args[0];
int port = Integer.parseInt(args[1]);
Socket clientSocket = new Socket(hostname, port);
System.out.println("Welcome to the online shopping site\nPlease enter your Username and Password");
BufferedReader username = new BufferedReader(new InputStreamReader(System.in));
BufferedReader password = new BufferedReader(new InputStreamReader(System.in));

DataOutputStream outToServer1 = new DataOutputStream(clientSocket.getOutputStream());
DataOutputStream outToServer2 = new DataOutputStream(clientSocket.getOutputStream());

BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
user = username.readLine();
pwd = password.readLine();

outToServer1.writeBytes(user+'\n');
outToServer2.writeBytes(pwd+'\n');

ack = inFromServer.readLine();
System.out.println(ack);
String credentials = "";
credentials = inFromServer.readLine();
System.out.println(credentials);

clientSocket.close();
}
}
