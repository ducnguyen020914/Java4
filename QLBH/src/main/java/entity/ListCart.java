package entity;

import java.util.List;

public class ListCart {
  private List<cart> order;
  private boolean status;
  private User u;
public List<cart> getOrder() {
	return order;
}
public void setOrder(List<cart> order) {
	this.order = order;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public User getU() {
	return u;
}
public void setU(User u) {
	this.u = u;
}
    
  
  
}
