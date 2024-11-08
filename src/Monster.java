import javax.swing.ImageIcon;

abstract class Monster {
    int life;
    ImageIcon monsIcon;
    Monster(int n){
        this.life = n;
    }
    abstract String sound();
    abstract ImageIcon getMonsIcon();

}

class Monster1 extends Monster{
    ImageIcon monsIcon = new ImageIcon(getClass().getResource("monster1.png"));

    Monster1(int n){
        super(n);
    }
    @Override
    String sound(){
        return "Caw!!";
    }
    @Override
    ImageIcon getMonsIcon() {
        return monsIcon;
    }
    
}

class Monster2 extends Monster{
    ImageIcon monsIcon = new ImageIcon(getClass().getResource("monster2.png"));

    Monster2(int n){
        super(n);
    }
    @Override
    String sound(){
        return "Moo!!";
    }
    @Override
    ImageIcon getMonsIcon() {
        return monsIcon;
    }

    
}

class Monster3 extends Monster{
    ImageIcon monsIcon = new ImageIcon(getClass().getResource("monster3.png"));

    Monster3(int n){
        super(n);
    }
    @Override
    String sound(){
        return "Pipo!!";
    }
    @Override
    ImageIcon getMonsIcon() {
        return monsIcon;
    }

    Boolean CantDefense(){
        return true;
    }
}