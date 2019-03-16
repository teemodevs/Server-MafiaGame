package server;

import network.UserConnector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerFrame extends JFrame {
    private static ServerFrame serverFrame = new ServerFrame();
    private JTextArea textArea;

    private ServerFrame() {
        textArea = new JTextArea();
    }

    public JTextArea getTextArea(){
        return textArea;
    }

    public static ServerFrame getInstance() {
        return serverFrame;
    }
    /**
     * boot Server with GUI
     */
    void boot() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 280, 400);

        /* ContentPanel 세팅 - 기본 프레임 */
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        setContentPane(contentPanel);

        /* 콘솔 창 세팅 */
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setEditable(false);

        /* 스크롤 바 세팅 */
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(0, 0, 264, 254);
        scroll.setViewportView(textArea);
        contentPanel.add(scroll);

        /* 실행 버튼 */
        JButton startButton = new JButton("서버 실행");
        startButton.addActionListener(new ServerStartAction());

        startButton.setBounds(0, 325, 264, 37);
        contentPanel.add(startButton);

        setVisible(true);
    }

    /**
     * Server Start Action Listener
     */
    class ServerStartAction implements ActionListener {
        // 버튼 클릭하면 유저를 받기 시작
        public void actionPerformed(ActionEvent e) {
            new UserConnector().run();
        }
    }
}
