import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI {
    private static JFrame _mainFrame;
    private static JPanel _dotPanel;
    private static JPanel _buttonPanel;
    private static JLabel _messageLabel;
    private static JLabel _scoreLabel = new JLabel("Score: 0");
    private static JLabel _movesLabel = new JLabel("Moves Left: " + 0);
    private static HashMap<Integer, String> _colorMap;
    private static Board _currBoard;
    private static int _size;
    private static JLabel[][] _dotLabels;

    public static void initGUI() {
        _mainFrame = new JFrame();
        _mainFrame.setLayout(null);
        _mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _mainFrame.setSize(800, 600);
        _mainFrame.setVisible(true);
        _mainFrame.setTitle("2048 VEERSUVRAT RAJPAL");
        _colorMap = new HashMap<Integer, String>();
        _colorMap.put(0, "images/0.png");
        _colorMap.put(2, "images/2.png");
        _colorMap.put(4, "images/4.png");
        _colorMap.put(8, "images/8.png");
        _colorMap.put(16, "images/16.png");
        _colorMap.put(32, "images/32.png");
        _colorMap.put(64, "images/64.png");
        _colorMap.put(128, "images/128.png");
        _colorMap.put(256, "images/256.png");
        _colorMap.put(512, "images/512.png");
        _colorMap.put(1024, "images/1024.png");
        _colorMap.put(2048, "images/2048.png");
        _colorMap.put(4096, "images/4096.png");
        _colorMap.put(8192, "images/8192.png");
        _colorMap.put(16384, "images/16384.png");
        _dotPanel = new JPanel();
        
        _size = 4;
        _dotLabels = new JLabel[4][4];
        _buttonPanel = new JPanel();
        _buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton button = new JButton("Left");
        button.addActionListener(new SwipeLEFT());
        c.gridx = 0;
        c.gridy = 1;
        _buttonPanel.add(button, c);
        button = new JButton("Down");
        c.gridx = 1;
        c.gridy = 1;
        button.addActionListener(new SwipeDOWN());
        _buttonPanel.add(button, c);
        button = new JButton("Up");
        c.gridx = 1;
        c.gridy = 0;
        button.addActionListener(new SwipeUP()); 
        _buttonPanel.add(button, c);
        button = new JButton("Right");
        c.gridx = 2;
        c.gridy = 1;
        button.addActionListener(new SwipeRIGHT());
        _buttonPanel.add(button, c);
        

        JButton button2 = new JButton("New Game");
        button2.setBounds(rect(500, 300, button2.getPreferredSize()));
        button2.addActionListener(new doNewGame());
        _mainFrame.add(button2);
        
        _buttonPanel.setBounds(rect(500, 200, _buttonPanel.getPreferredSize()));
        _mainFrame.add(_buttonPanel);
        _messageLabel = new JLabel(
                "this is long text that is just meant to adjust the \n"
                        + "preferred size of the message label. just ignore it as it will be \n"
                        + "replaced by nothing.\n\n");
        _messageLabel
                .setBounds(rect(450, 250, _messageLabel.getPreferredSize()));
        _messageLabel.setText("");

        _scoreLabel = new JLabel("Score: 9999999999");
        _scoreLabel.setBounds(rect(500, 150, _scoreLabel.getPreferredSize()));
        _scoreLabel.setText("Score: 0");

        _movesLabel = new JLabel("Moves: 9999999999");
        _movesLabel.setBounds(rect(475, 165, _movesLabel.getPreferredSize()));
        _movesLabel.setText("Moves: unlimited");

        _scoreLabel.setVisible(true);
        _messageLabel.setVisible(true);
        _movesLabel.setVisible(false);

        _scoreLabel.repaint();
        _messageLabel.repaint();
        _movesLabel.repaint();

        _mainFrame.add(_messageLabel);
        _mainFrame.add(_scoreLabel);

        _mainFrame.repaint();
        _mainFrame.validate();
    }

    
    public static void fillBoard() {
        _messageLabel.setText("");
        _scoreLabel.setText("Score: " + _currBoard.getScore());
        _movesLabel.setText("Moves: unlimited stuff");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        _dotPanel.removeAll();
        _dotPanel.setLayout(new GridBagLayout());
        JLabel block;
        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
            	BlockFor2048 gotFromBoard = _currBoard.getBoard()[i][j];
            	if (gotFromBoard == null){
            		block = dotLabel(0);
            	}else{
            		block = dotLabel(gotFromBoard.getValue());
            	}
                c.gridx = i;
                c.gridy = _size - j - 1;
                _dotLabels[i][j] = block;
                _dotPanel.add(block, c);
                block.setPreferredSize(new Dimension(120, 120));
            }
        }
        _dotPanel.setBounds(rect(0, 0, _dotPanel.getPreferredSize()));
        _dotPanel.validate();
        _mainFrame.add(_dotPanel);
        _mainFrame.validate();
    }

    private static Rectangle rect(int x, int y, Dimension size) {
        Rectangle result = new Rectangle(new Point(x, y), size);
        return result;
    }

    private static JLabel dotLabel(int blockValue) {
        JLabel block = new JLabel(new ImageIcon(_colorMap.get(blockValue)));
        block.setBorder(null);
        return block;
    }

    private static class SwipeLEFT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _currBoard.SwipeLeft();
            fillBoard();
            _scoreLabel.setText("Score: " + _currBoard.getScore());
            _mainFrame.validate();
        }
    }
    
    
    private static class SwipeRIGHT implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _currBoard.SwipeRight();
            fillBoard();
            _scoreLabel.setText("Score: " + _currBoard.getScore());
            _mainFrame.validate();
        }
    }
    
    private static class SwipeUP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _currBoard.SwipeUp();
            fillBoard();
            _scoreLabel.setText("Score: " + _currBoard.getScore());
            _mainFrame.validate();
        }
    }
    
    private static class SwipeDOWN implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _currBoard.SwipeDown();
            fillBoard();
            _scoreLabel.setText("Score: " + _currBoard.getScore());
            _mainFrame.validate();
        }
    }


    private static class doNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	_currBoard = new Board();
            _buttonPanel.setVisible(true);
            _mainFrame.validate();
            fillBoard();
        }
    }
    
}
