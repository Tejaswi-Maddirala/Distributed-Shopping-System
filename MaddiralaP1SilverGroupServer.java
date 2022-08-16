import java.io.*;
import java.net.*;
public class MaddiralaP1SilverGroupServer{
public static void main(String args[]) throws Exception {
ServerSocket receiver1 = new ServerSocket(16111);
int clientSent;
String pointsString;
int points = 0;

Socket connSocket1 = receiver1.accept();
DataOutputStream outToClient1 = new DataOutputStream(connSocket1.getOutputStream());

String optionsToBuy = "1 Bag 2 2000\n2 Suitcase 4 800\n3 Instapot 3 1000\n4 Mattress 6 700\n5 Refrigerator 8 950\n";
String[] itemNames = {"Bag", "Suitcase", "Instapot", "Mattress", "Refrigerator"};
int[] itemQuantity = {2, 4, 3, 6, 8};
int[] price = {2000, 800, 1000, 700, 950};

outToClient1.writeBytes(optionsToBuy);

BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(connSocket1.getInputStream()));

clientSent = Integer.parseInt(inFromClient1.readLine());
pointsString = inFromClient1.readLine();
points = Integer.parseInt(pointsString.trim());

int index = clientSent-1;
System.out.println("You wish to purchase "+ itemNames[index] +" which costs "+ price[index]);
itemQuantity[index] = itemQuantity[index]-1;
points = points - price[index];
System.out.println("Updated  points are "+ points);


}
}
