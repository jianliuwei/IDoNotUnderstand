package tech.liujianwei.socket.multiple;

import java.io.*;
import java.net.Socket;

public class SocketServer implements Runnable {

    private Socket socket;
    private int clientNo;

    public SocketServer(Socket socket, int clientNo) {
        this.socket = socket;
        this.clientNo = clientNo;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            do {
                double length = dis.readDouble();
                System.out.println("�ӿͻ���" + clientNo + "���յ��ı߳�����Ϊ��" + length);
                double result = length * length;
                dos.writeDouble(result);
                dos.flush();
            } while (dis.readInt() != 0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("��ͻ���" + clientNo + "ͨ�Ž���");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}