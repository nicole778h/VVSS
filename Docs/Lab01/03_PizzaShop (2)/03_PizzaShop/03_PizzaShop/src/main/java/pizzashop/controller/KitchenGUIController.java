package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.util.Calendar;

public class KitchenGUIController {
    @FXML
    private ListView<String> kitchenOrdersList; // Adăugare de tip generic pentru siguranță

    @FXML
    public Button cook;
    @FXML
    public Button ready;

    public static ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder;
    private Calendar now = Calendar.getInstance();
    private String extractedTableNumberString = "";
    private int extractedTableNumberInteger;

    private volatile boolean running = true; // Variabilă de control pentru oprirea thread-ului

    // Thread pentru actualizarea comenzilor în listă
    public Thread addOrders = new Thread(() -> {
        while (running) { // Verificare a condiției de oprire
            Platform.runLater(() -> kitchenOrdersList.setItems(order));
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                break; // Oprire thread la întrerupere
            }
        }
    });

    public void initialize() {
        addOrders.setDaemon(true);
        addOrders.start();

        // Controller pentru butonul Cook
        cook.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                kitchenOrdersList.getItems().remove(selectedOrder);
                kitchenOrdersList.getItems().add(selectedOrder.toString()
                        .concat(" Cooking started at: ").toUpperCase()
                        .concat(now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE)));
            }
        });

        // Controller pentru butonul Ready
        ready.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                kitchenOrdersList.getItems().remove(selectedOrder);
                if (selectedOrder.toString().length() >= 6) { // Prevenirea excepțiilor
                    extractedTableNumberString = selectedOrder.toString().substring(5, 6);
                    try {
                        extractedTableNumberInteger = Integer.parseInt(extractedTableNumberString);
                        System.out.println("--------------------------");
                        System.out.println("Table " + extractedTableNumberInteger + " ready at: " +
                                now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE));
                        System.out.println("--------------------------");
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing table number: " + extractedTableNumberString);
                    }
                }
            }
        });
    }

    // Metodă pentru oprirea thread-ului la închiderea interfeței
    public void stopThread() {
        running = false;
    }
}
