package q2;

public class ExpressieKnoop extends Knoop<Expressie> {

	public ExpressieKnoop(Knoop<Expressie> leftChild,
			Knoop<Expressie> rightChild, Expressie userObject) {
		super(leftChild, rightChild, userObject);
	}
	
	public Double bereken() {
		
		ExpressieKnoop temp;
		Expressie expressie = super.get();
		
		if(isEndNode()){
			// einde is altijd een getal en geeft dus de waarde terug
			// parameters maken hierbij niet uit, dus 0,0 is prima
			return expressie.calc(0,0);
			
		}else if((temp = hasLeftChild()) != null){
			
			// heeft een linker kind, dus bereken door op linker kind
			// maar rechter kind is een waarde, parameters niet relevant dus 0,0
			return expressie.calc(temp.bereken(), expressie.calc(0, 0));
			
		}else if((temp = hasRightChild()) != null){
			
			// heeft een recheter kind, dus bereken door op rechter kind
			// maar linker kind is een waarde, parameters niet relevant dus 0,0
			return expressie.calc( expressie.calc(0, 0),temp.bereken());
		}else{
			
			// twee knopen, bereken dus door op beiden
			ExpressieKnoop links = (ExpressieKnoop) super.getLeft();
			ExpressieKnoop rechts = (ExpressieKnoop) super.getRight();
			return expressie.calc(links.bereken(), rechts.bereken());
		}		 
	}
	
	// als deze knoop géén kinderen heeft, dan is het een eindknoop
	private boolean isEndNode(){
		if(super.getLeft() == null && super.getRight() == null){
			return true;
		}else{
			return false;
		}
	}
	
	// als deze knoop alleen een linker kind heeft, geef dit terug
	private ExpressieKnoop hasLeftChild(){
		if(super.getLeft()!=null && super.getRight() == null){
			return (ExpressieKnoop)super.getLeft();
		}
		return null;
	}
	
	// als deze knoop alleen een rechter kind heeft, geef dit terug
	private ExpressieKnoop hasRightChild(){
		if(super.getRight()!=null && super.getLeft() == null){
			return (ExpressieKnoop)super.getRight();
		}
		return null;
	}

}
