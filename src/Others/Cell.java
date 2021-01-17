package Others;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Translate;

public class Cell extends Group {
    private Rectangle rectangle;
    private Translate position;
    private boolean haveBorder = false;

    public Cell(int i) {
        position = new Translate();
        rectangle = new Rectangle();
        rectangle.getTransforms().add(position);
        if (i == 0)
            rectangle.setFill(Color.GREY);
        else
            rectangle.setFill(Color.WHITE);
        getChildren().add(rectangle);
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
    }

    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        position.setX(x);
        position.setY(y);
    }

    public void cellBorder(Color color) {
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeWidth(4);
        rectangle.setStroke(color);
        if (color == Color.GREEN)
            haveBorder = true;
    }

    public void removeCellBorder() {
        rectangle.setStroke(null);
        haveBorder = false;
    }

    public boolean isHaveBorder() {
        return (haveBorder);
    }

    public Rectangle getRectangle() {
        return (rectangle);
    }


}
