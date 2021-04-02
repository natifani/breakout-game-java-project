
package icons;
import javax.swing.ImageIcon;

public class ComponentContext {

	private Component component;
	
	public ComponentContext(Component component) {
		
		this.component = component;
	}
	
	public ImageIcon[] getComponent() {
		
		return component.getComponent();
	}
	
	
}
