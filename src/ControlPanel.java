import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton addCarButton = new JButton("Add car");

    JButton removeCarButton = new JButton("Remove car");
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Turbo on");
    JButton turboOffButton = new JButton("Turbo off");
    JButton liftBedButton = new JButton("Raise platform");
    JButton lowerBedButton = new JButton("Lower platform");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    public ControlPanel(VehicleAction carC, int X, int Y) {

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton,6 );
        controlPanel.add(removeCarButton,7 );
        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.black);


        startButton.setBackground(Color.green);
        startButton.setForeground(Color.black);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);


        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);

        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.startCars();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.stopCars();
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.brake(gasAmount);
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOn();
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.turboOff();
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.raisePlatform();
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.lowerPlatform();
            }
        });

        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.addCar();
            }
        });

        removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.removeCar();
            }
        });
    }
}
