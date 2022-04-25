/**
 *
 * @author Ma Man To Tony
 */
public class MStart {
    
    public static void main(String[] args) {
        
        MModel model = new MModel();
        MView view = new MView();
        MController controller = new MController();
    
        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);
        model.setController(controller);
        
    }
}
