package ie.cct.CBWA.CAFarmProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CAFarmController {

	private List<Farm> farmitem;

	public CAFarmController() {
		farmitem = new ArrayList<Farm>();// farm items array list

	}

	// Post http://localhost:8080/add-animal
	// this method will add a new animal ( list in txt added to the folder with few
	// animals data)
	@PostMapping("add-animal")
	public SuccessResponse addAnimal(@RequestBody Farm animal) {

		farmitem.add(animal);

		int count = 0;
		count = farmitem.size();
		return new SuccessResponse(
				"Farm animal:" + animal.getAnimal() + " added. Now there are " + count + " animals in farm");
	}

	// Get http://localhost:8080/average-price
	// this method will calculate the average weight of added animals
	@GetMapping("average-weight")
	public Float avgWeight() {

		if (farmitem.size() == 0) {
			throw new NotFoundException(" No animal found in the Farm");
		}
		float weight = 0.0f;
		for (Farm animal : farmitem) {
			weight += animal.getWeight();
		}
		weight = weight / farmitem.size();
		return weight;

	}

	// Get http://localhost:8080/chickentosell
	// this method will check how many cows are available to be sold according to
	// their weight
	@GetMapping("chickentosell")
	public int chickentosell() {
		int c = 0;
		
		if (farmitem.size() == 0) {
			throw new NotFoundException(" No animal found in the Farm");
		}
		for (Farm animal : farmitem) {
			if ((animal.getWeight() >= 0.5) && (animal.getWeight() < 10)) {
				c++;

			}
		}
		return c;
	}

	// Get http://localhost:8080/cowtosell
	// this method will check how many cows are available to be sold according to
	// their weight
	@GetMapping("cowtosell")
	public int cowtosell() {
		int c = 0;

		if (farmitem.size() == 0) {
			throw new NotFoundException(" No animal found in the Farm");
		}
		for (Farm animal : farmitem) {
			if (animal.getWeight() >= 350) {

				c++;
			}
		}

		return c;
	}

	// Get http://localhost:8080/currentValue
	// This method show what is the current value of the farm assuming the price of
	// each animal is set by a parameter in the HTTP request. This is an example: -
	// http://localhost:8080/currentValue?cow=350&pig=120&chicken=1
	@GetMapping("currentValue")
	public int currentValue(@RequestBody(required = true) int cow, @RequestBody(required = true) int pig, @RequestBody(required = true) int chicken) {
		int c = 0;
		int c2 = 0;
		int p = 0;
		int total = 0;
		
		if (farmitem.size() == 0) {
			throw new NotFoundException(" No animal found in the Farm");
		}
		for (Farm animal : farmitem) {
			if ((animal.getWeight() >= 0.5) && (animal.getWeight() < 10)) {
				c++;

			} else if ((animal.getWeight() >= 100) && (animal.getWeight() < 200)) {

				p++;

			}

			else if (animal.getWeight() >= 350) {

				c2++;
			}

			int valuepig = 0;
			int valuecow = 0;
			int valuechicken = 0;
			valuepig = p * pig;
			valuecow = c2 * cow;
			valuechicken = c * chicken;
			total = valuepig + valuechicken + valuecow;
		}

		return total;
	}

	// Get http://localhost:8080/farmstock
	// this method will check how many animals are available to be sold according to
	// their weight, and will show the price of all the animals that can be sold
	// right now.
	@GetMapping("farmstock")
	public float farmstock() {
		int c = 0;
		int c2 = 0;
		int p = 0;
		int total = 0;
		if (farmitem.size() == 0) {
			throw new NotFoundException(" No animal found in the Farm");
		}
		
		for (Farm animal : farmitem) {
			if ((animal.getWeight() >= 0.5) && (animal.getWeight() < 10)) {
				c += animal.getPrice();

			} else if ((animal.getWeight() >= 100) && (animal.getWeight() < 200)) {

				p += animal.getPrice();

			}

			else if (animal.getWeight() >= 350) {

				c2 += animal.getPrice();
			}

			total = c + c2 + p;
		}

		return total;
	}

	// Get http://localhost:8080/pigtosell
// this method will check how many pigs are available to be sold according to their weight
	@GetMapping("pigtosell")
	public int pigtosell() {
		int p = 0;
		if (farmitem.size() == 0) {
			throw new NotFoundException(" No animal found in the Farm");
		}
		
		for (Farm animal : farmitem) {
			if ((animal.getWeight() >= 100) && (animal.getWeight() < 200)) {

				p++;

			}
		}

		return p;
	}
}
