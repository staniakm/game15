package game;

class GameNode {
        private GameNode leftNode;
        private GameNode rightNode;
        private GameNode topNode;
        private GameNode bottomNode;

    private final int value;

    GameNode(int value) {
        if (value < 0 || value > 15)
            throw new IllegalStateException("0 - 15");
        this.value = value;
    }

    private boolean isEmpty(){
        return value == 0;
    }

    public int getValue() {
        return value;
    }


    public void setLeftNode(GameNode leftNode) {
        this.leftNode = leftNode;
        if (leftNode!=null)
        leftNode.rightNode = this;
    }

    public void setRightNode(GameNode rightNode) {
        this.rightNode = rightNode;
        if (rightNode!=null)
        rightNode.leftNode = this;
    }

    public void setTopNode(GameNode topNode) {
        this.topNode = topNode;
        if (topNode!=null)
        topNode.bottomNode = this;
    }

    public void setBottomNode(GameNode bottomNode) {
        this.bottomNode = bottomNode;
        if (bottomNode!=null)
        bottomNode.topNode = this;
    }

    public GameNode getLeftNode() {
        return leftNode;
    }

    public GameNode getRightNode() {
        return rightNode;
    }

    public GameNode getTopNode() {
        return topNode;
    }

    public GameNode getBottomNode() {
        return bottomNode;
    }

    public boolean moveNode(int number, int i){
        boolean status = false;
        GameNode node = getNode(number,i);
        if (node.getLeftNode()!=null && node.getLeftNode().isEmpty()){
            node.moveLeft();
            status = true;
        }else if (node.getRightNode()!=null && node.getRightNode().isEmpty()){
            node.moveRight();
            status = true;
        }
        else if (node.getTopNode()!=null && node.getTopNode().isEmpty()){
            node.moveTop();
            status = true;
        }
        else if (node.getBottomNode()!=null && node.getBottomNode().isEmpty()){
            node.moveBottom();
            status = true;
        }
        return status;
    }

    private void moveBottom() {
        GameNode tmp0 = this.getBottomNode();
        GameNode left = this.getLeftNode();
        GameNode right = this.getRightNode();
        GameNode top = this.getTopNode();
        GameNode bottom = this.getBottomNode().getBottomNode();

        this.setTopNode(tmp0);
        this.setBottomNode(bottom);
        this.setLeftNode(tmp0.getLeftNode());
        this.setRightNode(tmp0.getRightNode());
        tmp0.setRightNode(right);
        tmp0.setTopNode(top);
        tmp0.setLeftNode(left);
    }

    private void moveTop() {
        GameNode tmp0 = this.getTopNode();
        GameNode left = this.getLeftNode();
        GameNode right = this.getRightNode();
        GameNode top = this.getTopNode().getTopNode();
        GameNode bottom = this.getBottomNode();

        this.setTopNode(top);
        this.setBottomNode(tmp0);
        this.setLeftNode(tmp0.getLeftNode());
        this.setRightNode(tmp0.getRightNode());
        tmp0.setRightNode(right);
        tmp0.setBottomNode(bottom);
        tmp0.setLeftNode(left);

    }

    private void moveRight() {
        GameNode tmp0 = this.getRightNode();
        GameNode left = this.getLeftNode();
        GameNode right = this.getRightNode().getRightNode();
        GameNode top = this.getTopNode();
        GameNode bottom = this.getBottomNode();

        this.setTopNode(tmp0.getTopNode());
        this.setBottomNode(tmp0.getBottomNode());
        this.setLeftNode(tmp0);
        this.setRightNode(right);
        tmp0.setTopNode(top);
        tmp0.setBottomNode(bottom);
        tmp0.setLeftNode(left);
    }

    private void moveLeft() {

        GameNode tmp0 = this.getLeftNode();
        GameNode left = this.getLeftNode().getLeftNode();
        GameNode right = this.getRightNode();
        GameNode top = this.getTopNode();
        GameNode bottom = this.getBottomNode();

        this.setTopNode(tmp0.getTopNode());
        this.setBottomNode(tmp0.getBottomNode());
        this.setLeftNode(left);
        this.setRightNode(tmp0);
        tmp0.setTopNode(top);
        tmp0.setBottomNode(bottom);
        tmp0.setRightNode(right);
    }

    private GameNode getNode(int number, int i) {
        if (number==0 && i==0){
            return this;
        }else {
            if (number>0){
               return this.getRightNode().getNode(number-1,i);
            }else {
              return this.getBottomNode().getNode(number,i-1);
            }
        }
    }

    public void getCoordinate(GameNode topLeft) {
        int letter=0;
        int number=0;
        GameNode node = this;
        while(node.getLeftNode()!=null){
            node = node.getLeftNode();
            letter++;
        }
        while (node.getTopNode()!=null){
            node = node.getTopNode();
            number++;
        }

        topLeft.moveNode (letter,number);

    }

    public GameNode getNewTopleftNode() {
        int letter=0;
        int number=0;
        GameNode node = this;
        while(node.getLeftNode()!=null){
            node = node.getLeftNode();
            letter++;
        }
        while (node.getTopNode()!=null){
            node = node.getTopNode();
            number++;
        }
        return getTopNode(letter,number);

    }

    private GameNode getTopNode(int number, int i) {
        if (number==0 && i==0){
            return this;
        }else {
            if (number>0){
                return this.getLeftNode().getNode(number-1,i);
            }else {
                return this.getTopNode().getNode(number,i-1);
            }
        }
    }
}
