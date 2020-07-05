package ie.cct.CBWA.CAFarmProject;

public class Farm {

	private String  animal;
	private float price;
	private float weight;
	
	
	
	public Farm() {
		
		
	}
	
	public Farm(String animal, float price, float weight) {
		
		this.animal = animal;
		this.price = price;
		this.weight = weight;
	}
	
	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
		
}
