package com.flatten.json;
import java.io.*;
import java.util.Scanner;
import org.json.JSONObject;

public class JsonFlatten {

	public void traverseTree(Node root, StringBuilder mergedKey, JSONObject outputJson) {

		if (root == null) {
			return;
		}

		for (int i = 0; i < root.links.length; i++) {
			Node node = root.links[i];
			if (node != null) {

				//merge the json key separated by "."
				StringBuilder tempString = new StringBuilder();
				tempString.append(".").append(node.key);
				mergedKey.append(tempString);
				//If the value is a json perform recursion
				if (node.links != null) {
					traverseTree(node, mergedKey, outputJson);
				} else if (node.val != null) {
					outputJson.put(mergedKey.toString(), node.val);
				}

				mergedKey.delete(mergedKey.length() - tempString.length(), mergedKey.length());
			}
		}
	}

	public void writeToJsonFile(String outputFilePath, JSONObject outputJson) {
		//Write To Json file
		try (FileWriter outputFile = new FileWriter(outputFilePath)) {
			outputFile.write(outputJson.toString());
			outputFile.flush();
		} catch (IOException e) {
			System.out.println("IO Exception while writing json to file:: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void parseJsonFile(String inputFilePath) {
		JSONObject outputJson = new JSONObject();
		//Read Json File in JsonObject Format
		//JSONObject jsonObject = readJsonFile(inputFilePath);
		JSONObject jsonObject = new JSONObject(inputFilePath);

		//Parse Json File 
		for (String key : jsonObject.keySet()) {
			Object value = jsonObject.get(key);

			//If the value does not have a nested json, put the value in output
			if (!(value instanceof JSONObject)) {
				outputJson.put(key, value);
			} else {

				//If the value is a nested json, perform depth first search
				Node root = new Node(key, value);
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(root.key);
				traverseTree(root, stringBuilder, outputJson);
			}
		}
		System.out.println("Output:");
		System.out.println(outputJson);

	}

	public static void main(String[] args) throws IOException {

		if ( args.length == 0 ) {
			Scanner sc = new Scanner(new InputStreamReader(System.in));
			StringBuilder stringBuilder = new StringBuilder();
			String line = "";
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				stringBuilder.append(line);
			}
			JsonFlatten jsonFlatten = new JsonFlatten();
			jsonFlatten.parseJsonFile(stringBuilder.toString());

		}
	}

}
