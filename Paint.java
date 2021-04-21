import java.applet.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Button;
import java.util.ArrayList;
public class Paint extends Applet implements MouseListener , MouseMotionListener{
	int x1, x2, y1, y2 ,sel_Shape,sel_Color;
    boolean flag = false;
    ArrayList<Shape> geoShape =new ArrayList<Shape>(); 
    Oval oval=new Oval();
    Rectangle rect= new Rectangle();
    Line line = new Line();
    Eraser eraser = new Eraser();
    Button ovalButton= new Button("oval");
    Button rectButton =new Button("Rectangle");
    Button freehandButton= new Button("Free Hand");
    Button lineButton= new Button("Line");
    Button redButton = new Button();
    Button greenButton= new Button();
    Button blueButton = new Button();
    Button eraserButton = new Button("Eraser");
    Button clearAllButton = new Button("Clear All");
    Button undoButton= new Button("Undo");
    Checkbox filledButton = new Checkbox("Fill Shape", flag); 

    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
        add(redButton);
        add(greenButton);
        add(blueButton);
        add(ovalButton);
        add(rectButton);
        add(freehandButton);
        add(lineButton);
        add(eraserButton);
        add(clearAllButton);
        add(undoButton);
        add(filledButton);
        redButton.setBackground(Color.RED);
        redButton.setSize(100,30);
        greenButton.setBackground(Color.GREEN);
        greenButton.setSize(100,30);
        blueButton.setBackground(Color.BLUE);
        blueButton.setSize(100,30);
        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Shape = 1;
            }
        });
       rectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Shape = 2;
            }
        });

       freehandButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Shape = 3;
            }
        });

        lineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Shape = 4;
            }
        });

        eraserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Shape = 5;
            }
        });

        redButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Color = 1;
            }
        });
       greenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Color = 2;
            }
        });
        blueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sel_Color = 3;
            }
        });

        clearAllButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                geoShape.clear();
                switch (sel_Shape) {
                    case 1:
                        oval.setX1(0);
                        oval.setY1(0);
                        oval.setX2(0);
                        oval.setY2(0);
                        break;
                    case 2:
                        rect.setX1(0);
                        rect.setY1(0);
                        rect.setX2(0);
                        rect.setY2(0);
                        break;
                    case 4:
                        line.setX1(0);
                        line.setY1(0);
                        line.setX2(0);
                        line.setY2(0);
                        break;

                }
                repaint();
            }
        });

        filledButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                flag = filledButton.getState();
            }
        });

        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (geoShape.size() > 0) {
                    geoShape.remove(geoShape.size() - 1);
                    switch (sel_Shape) {
                        case 1:
                            oval.setX1(0);
                            oval.setY1(0);
                            oval.setX2(0);
                            oval.setY2(0);
                           break;
                        case 2:
                            rect.setX1(0);
                            rect.setY1(0);
                            rect.setX2(0);
                            rect.setY2(0);
                            break;
                        case 4:
                            line.setX1(0);
                            line.setY1(0);
                            line.setX2(0);
                            line.setY2(0);
                            break;
                    }
                    repaint();
                }
            }
        });      
    }
  public void paint(Graphics g) {

        for (int i = 0; i < geoShape.size(); i++) {
            geoShape.get(i).drawShape(g);
        }

        switch (sel_Shape) {
            case 1:
                oval.setFlag(flag);
                oval.setCur_Color(sel_Color);
                oval.drawShape(g);
                break;
            case 2:
                rect.setFlag(flag);
                rect.setCur_Color(sel_Color);
                rect.drawShape(g);
                break;
            case 4:
                line.setFlag(flag);
                line.setCur_Color(sel_Color);
                line.drawShape(g);
                break;
            case 5:
                eraser.drawShape(g);
                break;
        }

    }
   @Override
    public void mouseClicked(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        x2 = e.getX();
        y2 = e.getY();

        switch (sel_Shape) {
            case 4:
                line.setX1(x1);
                line.setY1(y1);
                line.setX2(x2);
                line.setY2(y2);
                break;
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
        x2 = e.getX();
        y2 = e.getY();

        switch (sel_Shape) {
            case 1:
                oval.setX1(x1);
                oval.setY1(y1);
                break;
            case 2:
                rect.setX1(x1);
                rect.setY1(y1);
                break;
            case 3:
                line.setX1(x1);
                line.setY1(y1);
                break;
            case 4:
                line.setX1(x1);
                line.setY1(y1);
                line.setX2(x2);
                line.setY2(y2);
                break;
            case 5:
               eraser.setX1(x1);
               eraser.setY1(y1);
                break;
        }

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (sel_Shape) {
            case 1:
                geoShape.add(new Oval(x1, y1, x2, y2, sel_Color, flag));
                break;
            case 2:
                geoShape.add(new Rectangle(x1, y1, x2, y2, sel_Color, flag));
                break;
            case 4:
                geoShape.add(new Line(x1, y1, x2, y2, sel_Color, flag));
                break;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        switch (sel_Shape) {
            case 1:
                oval.setX2(x2);
                oval.setY2(y2);
                break;
            case 2:
                rect.setX2(x2);
                rect.setY2(y2);
                break;
            case 3:
                line.setX2(x2);
                line.setY2(y2);
                geoShape.add(new Line(x1, y1, x2, y2, sel_Color, flag));  
                x1 = e.getX();      
                y1 = e.getY();
                break;
            case 4:
                line.setX2(x2);
                line.setY2(y2);
                break;
            case 5:
                eraser.setX2(x2);
                eraser.setY2(y2);
                geoShape.add(new Eraser(x1, y1, 25, 25, sel_Color, flag));   
                x1 = e.getX();      
                y1 = e.getY();
                break;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {} 
}


 class Shape {

    private int x1, y1, x2, y2 ,cur_Color;
    boolean flag;

     Shape(int x1, int y1, int x2, int y2, int cur_Color, boolean flag) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.cur_Color = cur_Color;
        this.flag = flag;
    }

    Shape(){}

    public void setX1(int x1) {
        this.x1 = x1;
    }
    public int getX1() {
        return x1;
    }
   public void setY1(int y1) {
        this.y1 = y1;
    }
    public int getY1() {
        return y1;
    }
    public void setX2(int x2) {
        this.x2 = x2;
    }
    public int getX2() {
        return x2;
    }
    public void setY2(int y2) {
        this.y2 = y2;
    }
    public int getY2() {
        return y2;
    }

    public void setCur_Color(int cur_Color) {
        this.cur_Color = cur_Color;
    }

    public int getCur_Color() {
        return cur_Color;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void drawShape(Graphics g) {
    }

}
 class Rectangle extends Shape {
    int x,y,width,height;
     Rectangle() {}

     Rectangle(int x1, int y1, int x2, int y2, int cur_Color, boolean flag) {
        super(x1, y1, x2, y2, cur_Color, flag);
    }
    @Override
    public void drawShape(Graphics g) {
        switch (getCur_Color()) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            case 3:
                g.setColor(Color.BLUE);
                break;
            default:
                g.setColor(Color.BLACK);
                break;
        }
         x = Math.min(getX1(), getX2());
         y = Math.min(getY1(), getY2());
         width = Math.abs(getX1() - getX2());
         height = Math.abs(getY1() - getY2());
            if (isFlag() == true) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}
class Eraser extends Rectangle {
    Eraser() { }
    Eraser(int x1, int y1, int x2, int y2, int cur_Color, boolean flag) {
        super(x1, y1, x2, y2, cur_Color, flag);
    }
   @Override
    public void drawShape(Graphics g) {
        g.fillOval(getX1(), getY1(),25, 25);
        g.setColor(Color.WHITE);
    }
}
 class Oval extends Shape {
    
    int x,y,width,height;

     Oval() {}
     Oval(int x1, int y1, int x2, int y2, int cur_Color, boolean flag) {
        super(x1, y1, x2, y2, cur_Color, flag);
    }

    public void drawShape(Graphics g) {
        switch (getCur_Color()) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            case 3:
                g.setColor(Color.BLUE);
                break;
            default:
                g.setColor(Color.BLACK);
                break;
        }
         x = Math.min(getX1(), getX2());
         y = Math.min(getY1(), getY2());
         width = Math.abs(getX1() - getX2());
         height = Math.abs(getY1() - getY2());
         if (isFlag() == true) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }
}
class Line extends Shape {
  Line(int x1, int y1, int x2, int y2, int cur_Color, boolean flag) {
        super(x1, y1, x2, y2, cur_Color, flag);
    }
     Line() {}
    @Override
    public void drawShape(Graphics g) {
        switch (getCur_Color()) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            case 3:
                g.setColor(Color.BLUE);
                break;
            default:
                g.setColor(Color.BLACK);
                break;
                   }
        g.drawLine(getX1(), getY1(), getX2(), getY2());
    }
}









 