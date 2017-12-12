import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


public class LiteralExpression implements Expression, Focusable, CopyAble{

    private CompoundExpression parent;
    private String literal;
    private boolean focused;
    private Node node;

    /**
     * Converts the expression to string
     * @param indentLevel the indentation level
     * @return a String containing the expression indented
     */
    public String convertToString(int indentLevel){
        StringBuffer sb = new StringBuffer();
        Expression.indent(sb,indentLevel);
        return sb.append(literal + "\n").toString();
    }

    /**
     * Sets the parent of the expression
     * @param parent the CompoundExpression that should be the parent of the target object
     */

    public void setParent(CompoundExpression parent){
        this.parent = parent;
    }

    /**
     * Returns the parent of the expression
     * @return the parent of the expression
     */
    public CompoundExpression getParent() {
        return this.parent;
    }

    /**
     * Returns a deep copy of the expression
     * @return a deep copy of the expression
     */
    public LiteralExpression deepCopy(){
        LiteralExpression copy = new LiteralExpression();
        copy.literal = this.literal;
        return copy;
    }
    public Expression trueCopy(){
        if(this.getParent()!=null){
            AbstractCompoundExpression parent =  ((AbstractCompoundExpression)this.getParent());
            AbstractCompoundExpression copy = (AbstractCompoundExpression) parent.trueCopy();
            for(Expression e:copy.getChildren()){
                if(e.convertToString(0) == this.convertToString(0))
                    return e;
            }
            return null;
        }
        else {
            return this.deepCopy();
        }
    }



    @Override
    public Node getNode() {
        if(node == null) {
            final HBox hbox = new HBox();

            final Label text = new Label(this.literal);
            text.setFont(Font.font("Comic Sans MS", 36));

            hbox.getChildren().add(text);
            if (this.getFocused()) {
                hbox.setBorder(RED_BORDER);
            }
            node = hbox;
            return hbox;
        }
        return node;
    }

    /**
     * Sets the literal of the expression
     * @param Literal , the literal of the expression.
     */
    public void setLiteral(String Literal){
        this.literal = Literal;
    }

    /**
     * Flattens the expression
     * Does nothing, but higher functions may call it sometimes
     */
    public void flatten(){
    }

    @Override
    public boolean getFocused() {
        return this.focused;
    }

    @Override
    public void setFocused(boolean s) {
        this.focused = s;
    }
}
