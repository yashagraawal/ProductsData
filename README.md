Title: Product Data Management

Abstract:
This project outlines the systematic retrieval of product data from an API source and its subsequent insertion into a database.

Data Retrieval Procedure:
The application initiates data retrieval from the designated API endpoint:

API Endpoint: https://fakestoreapi.com/products
Data Format: JSON (Array of JSON Objects)
Execution Workflow:

- The application retrieves the data from the API endpoint, receiving a JSON-formatted response.
- The JSON data is parsed and stored as a string variable.
- Each JSON object within the string is mapped to the corresponding product class.
- Products are organized into a list structure for efficient management.
- A batch mechanism is employed to aggregate products for insertion into the database.
- Upon completion of the batch assembly, the consolidated data is seamlessly inserted into the database.