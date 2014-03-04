package com.javapapers.xml.jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBElement;

public class JAXBMarshalSample {

	public static void main(String[] args) throws FileNotFoundException {

		ZooInfo zoo = new ZooInfo();

		zoo.setZooId(987789);
		zoo.setZooName("Gir National Park");

		Animals animals = new Animals();

		List<Animals.Animal> animalsList = animals.getAnimal();

		Animals.Animal animal1 = new Animals.Animal();
		animal1.setAnimalName("Jaguar");
		animal1.setAnimalType("Wild");

		Animals.Animal animal2 = new Animals.Animal();
		animal2.setAnimalName("Goat");
		animal2.setAnimalType("Domestic");

		Animals.Animal animal3 = new Animals.Animal();
		animal3.setAnimalName("Puma");
		animal3.setAnimalType("Wild");

		animalsList.add(animal1);
		animalsList.add(animal2);
		animalsList.add(animal3);

		zoo.setAnimals(animals);

		// create an element for marshalling
		JAXBElement<ZooInfo> zooInfoElement = (new ObjectFactory()).createZoo(zoo);

		// create a Marshaller and marshal to System.out
		JAXB.marshal(zooInfoElement, new FileOutputStream(new File("marshalledZoo.xml")));
	}
}
