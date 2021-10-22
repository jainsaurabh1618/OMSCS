import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class DeliveryService {
	
	Map<String, Store> storeMap = new TreeMap<String, Store>();
	Map<String, Pilot> pilotMap = new TreeMap<String, Pilot>();
	Map<String, Customer> customerMap = new TreeMap<String, Customer>();
	Map<String, Drone> droneMap = new TreeMap<String, Drone>();
	Map<String, Item> itemMap = new TreeMap<String, Item>();
	Map<String, Order> orderMap = new TreeMap<String, Order>();

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("make_store")) {
                    //System.out.println("store: " + tokens[1] + ", revenue: " + tokens[2]);
                	if(storeMap.get(tokens[1]) == null) {
                		storeMap.put(tokens[1], new Store(tokens[1], tokens[2]));
                	}
                	System.out.println("OK:change_completed");

                } else if (tokens[0].equals("display_stores")) {
                	if(storeMap.size() > 0) {
                		for(Store store:storeMap.values()) {
                        	System.out.println("name:"+store.getName()+",revenue:"+store.getRevenue());
                        }
                	}
                    
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("sell_item")) {
                	if(storeMap.get(tokens[1])!=null) {
                		Item item = new Item(tokens[2], tokens[1], tokens[3]);
                		itemMap.put(tokens[1]+"_"+tokens[2], item);
                	}
                	
                    //System.out.println("store: " + tokens[1] + ", item: " + tokens[2] + ", weight: " + tokens[3]);
                	System.out.println("OK:change_completed");

                } else if (tokens[0].equals("display_items")) {
                	if(storeMap.get(tokens[1])!=null) {
                		for(String itemKey:itemMap.keySet()){
                			if(itemKey.startsWith(tokens[1])) {
                				Item item = itemMap.get(itemKey);
                				System.out.println(item.getName()+","+item.getWeight());
                			}
                			//System.out.println(item.getName()+","+item.getWeight());
                		}
                	}
                	
                	System.out.println("OK:display_completed");
                    //System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("make_pilot")) {
                	List<String> licenseIdList = new ArrayList<String>();
                	
                	if(pilotMap.size() > 0) {
                		for(Pilot pilot:pilotMap.values()) {
                			licenseIdList.add(pilot.getLicenseId());
                		}
                	}
                	
                	if(pilotMap.get(tokens[1]) == null && !licenseIdList.contains(tokens[6])) {
                		pilotMap.put(tokens[1], new Pilot(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
                	}
                    //System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    //System.out.println(", phone: " + tokens[4] + ", tax: " + tokens[5] + ", license: " + tokens[6] + ", experience: " + tokens[7]);
                	System.out.println("OK:change_completed");

                } else if (tokens[0].equals("display_pilots")) {
                    for(Pilot pilot:pilotMap.values()) {
                    	System.out.println("name:"+pilot.getFname()+"_"+pilot.getLname()+",phone:"+pilot.getPhoneNum()+",taxID:"+pilot.getTaxId()+",licenseID:"+pilot.getLicenseId()+",experience:"+pilot.getExpLvl());
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("make_drone")) {
                	if(storeMap.get(tokens[1]) != null) {
                		Drone drone = new Drone(tokens[2], tokens[1], tokens[3], tokens[4]);
                		droneMap.put(tokens[1]+"_"+tokens[2], drone);
                	}
                	
                	System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", capacity: " + tokens[3] + ", fuel: " + tokens[4]);

                } else if (tokens[0].equals("display_drones")) {
                	if(storeMap.get(tokens[1]) != null) {
                		for(String droneKey:droneMap.keySet()) {
                			if(droneKey.startsWith(tokens[1])) {
                				Drone drone = droneMap.get(droneKey);
                				int numOrder = 0;
                    			for(Order order:orderMap.values()) {
                    				if(drone.getId().equalsIgnoreCase(order.getDroneId())) {
                    					numOrder++;
                    				}
                    			}
                    			if(drone.getPilotAccount() != null) {
                    				Pilot pilot = pilotMap.get(drone.getPilotAccount());
                    				System.out.println("droneID:"+drone.getId()+",total_cap:"+drone.getWeightCapacity()+",num_orders:"+numOrder+",remaining_cap:"+(drone.getWeightCapacity() - drone.calculateDroneWeight(orderMap, itemMap))+",trips_left:"+drone.getRefuelThreshold()+",flown_by:"+pilot.getFname()+"_"+pilot.getLname());
                    			}else {
                    				System.out.println("droneID:"+drone.getId()+",total_cap:"+drone.getWeightCapacity()+",num_orders:"+numOrder+",remaining_cap:"+(drone.getWeightCapacity() - drone.calculateDroneWeight(orderMap, itemMap))+",trips_left:"+drone.getRefuelThreshold());
                    			}
                			}
                			
                		}
                	}
                	System.out.println("OK:display_completed");
                    //System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("fly_drone")) {
                	if(pilotMap.get(tokens[3]) != null && storeMap.get(tokens[1]) != null && droneMap.get(tokens[1]+"_"+tokens[2]) != null) {
                		droneMap.get(tokens[1]+"_"+tokens[2]).setPilotAccount(tokens[3]);
                		pilotMap.get(tokens[3]).setStoreName(tokens[1]);
                		pilotMap.get(tokens[3]).setDroneId(tokens[2]);
                	}
                	System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", pilot: " + tokens[3]);

                } else if (tokens[0].equals("make_customer")) {
                	
                    if(customerMap.get(tokens[1]) == null) {
                    	customerMap.put(tokens[1], new Customer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]));
                    }
                	//System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    //System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);
                    System.out.println("OK:change_completed");

                } else if (tokens[0].equals("display_customers")) {
                    for(Customer customer:customerMap.values()) {
                    	System.out.println("name:"+customer.getFname()+"_"+customer.getLname()+",phone:"+customer.getPhoneNum()+",rating:"+customer.getRating()+",credit:"+customer.getCredit());
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("start_order")) {
                	if(storeMap.get(tokens[1]) != null && orderMap.get(tokens[1]+"_"+tokens[2]) == null 
                			&& droneMap.get(tokens[1]+"_"+tokens[3]) != null && customerMap.get(tokens[4]) != null) {
                		orderMap.put((tokens[1]+"_"+tokens[2]), new Order(tokens[2], tokens[1], tokens[3], tokens[4]));
                		//storeMap.get(tokens[1]).startOrder(tokens[2], tokens[3], tokens[4]);
                	}
                	System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);

                } else if (tokens[0].equals("display_orders")) {
                	if(storeMap.get(tokens[1]) != null) {// && orderMap.get(tokens[1]).orderMap.size() > 0) {
                		for(String orderKey: orderMap.keySet()) {
                			if(orderKey.startsWith(tokens[1])) {
                				Order order = orderMap.get(orderKey);
                				System.out.println("orderID:"+order.getOrderId());
                    			if(order.lineItemMap.size() > 0) {
    	                			for(LineItem lineItem:order.lineItemMap.values()) {
    	                				String displayStr = "item_name:"+lineItem.getItemName();
    	                				displayStr = displayStr + ",total_quantity:" + lineItem.getQuantity();
    	                				displayStr = displayStr + ",total_cost:" + lineItem.getQuantity() * lineItem.getUnitPrice();
    	                				displayStr = displayStr + ",total_weight:" + lineItem.getQuantity() * itemMap.get(tokens[1]+"_"+lineItem.getItemName()).getWeight();
    	                				System.out.println(displayStr);
    	                			}
                    			}
                			}
                			
                		}
                	}
                	System.out.println("OK:display_completed");
                    //System.out.println("store: " + tokens[1]);

                } else if (tokens[0].equals("request_item")) {
                	if(storeMap.get(tokens[1]) != null && orderMap.get(tokens[1]+"_"+tokens[2]) != null
                			&& itemMap.get(tokens[1]+"_"+tokens[3]) != null) {
                		String custAcc = orderMap.get(tokens[1]+"_"+tokens[2]).getCustAcc();
                		
                		// Check customer credit
                		int custTotalOrderPrice = customerMap.get(custAcc).calculateTotalOrderCost(orderMap);
                		double newItemTotalPrice = Integer.parseInt(tokens[4]) * Double.parseDouble(tokens[5]);
                		
                		// Check drone capacity
                		
                		String droneId = orderMap.get(tokens[1]+"_"+tokens[2]).getDroneId();
                		int droneCapacity = droneMap.get(tokens[1]+"_"+droneId).getWeightCapacity();
                		int currDroneWeight = droneMap.get(tokens[1]+"_"+droneId).calculateDroneWeight(orderMap, itemMap);
                		
                		// Request Item
            			if((customerMap.get(custAcc).getCredit() - custTotalOrderPrice) >= newItemTotalPrice
            					&& (droneCapacity - currDroneWeight) >= (Integer.parseInt(tokens[4])*itemMap.get(tokens[1]+"_"+tokens[3]).getWeight())) {
            				orderMap.get(tokens[1]+"_"+tokens[2]).requestItem(tokens[3], tokens[4], tokens[5]);
            			}
            		}
                    System.out.println("OK:change_completed");
                	//System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);

                } else if (tokens[0].equals("purchase_order")) {
                	if(storeMap.get(tokens[1]) != null && orderMap.get(tokens[1]+"_"+tokens[2]) != null) {
                		Order order = orderMap.get(tokens[1]+"_"+tokens[2]);
                		Drone drone = droneMap.get(tokens[1]+"_"+order.getDroneId());	
                		int orderPrice = order.calculateOrderPrice();
                		if(drone.getPilotAccount() != null && drone.getRefuelThreshold() > 0) {
                			// Update store revenue
                			storeMap.get(tokens[1]).updateRevenue(orderPrice);
                			
                			//Adjust customer credit
                			String custAcc = order.getCustAcc();
                			int currCredit = customerMap.get(custAcc).getCredit();
                			customerMap.get(custAcc).setCredit(currCredit-orderPrice);
                			
                			//Increment pilot experiance
                			pilotMap.get(drone.getPilotAccount()).incrementExp();
                			
                			// Delete order
                			orderMap.remove(tokens[1]+"_"+tokens[2]);
                		}
                	}
                	System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("cancel_order")) {
                	if(storeMap.get(tokens[1]) != null && orderMap.get(tokens[1]+"_"+tokens[2]) != null) {
                		orderMap.remove(tokens[1]+"_"+tokens[2]);
                	}
                	System.out.println("OK:change_completed");
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}
