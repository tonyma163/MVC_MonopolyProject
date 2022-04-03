package test;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolyStart {
    
    public static void main(String[]args) {
        MonopolyModel model = new MonopolyModel();
        MonopolyView view = new MonopolyView();
        MonopolyController controller = new MonopolyController();
        
        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);
        model.setController(controller);
        
        view.processCommand();
    }
    
}
