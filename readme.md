# **Welcome to the Rest Item API**
### Features
This api provide different endpoints for get, update or save information related to Items.
### Technologies behind the API
- Java 8
- Spring boot
- Spring Rest
- Spring Security
- Spring Data
- H2 DATABASE
- JWT (Json Web Tokens)

Here is an example of an item data JSON:
####Item Example

        {
            "itemId":1,
            "itemName":"item_x",
            "itemEnteredByUser":"user_x",
            "itemEnteredDate":"2020-05-10T13:00:41.499",
            "itemBuyingPrice":50.0,
            "itemSellingPrice":55.0,
            "itemLastModifiedDate":"2020-05-10T13:00:41.498",
            "itemLastModifiedByUser":"user_y",
            "itemStatus":"AVAILABLE"
    }

###  Instalation Requirements
- JDK 1.8
- Spring Tool Suite
- Postman
- Git

You can clone the project using git or you can directly download the project from GitHub and then unzip the file. If you want to clone the project using git, please open the terminal and type:

`git clone https://github.com/mc11057/Rest-API-Spring.git`
## Running the app locally
## Configuring Spring Tool Suite
- Open Spring Tool Suite and import the cloned project like existing maven project
- After of finish the refresh you have to do a right click again and select the option maven, and then select the option update Project, it will appear a window, select the project and do click on ok button. 

Then look for Boot Dashboard window on Spring tool suite and you will see the application with name restapi, do right click on that and select the option (Re)start. Wait for a moment and the application it will running.

**¡Now, the Item Rest API is Running!**  we can do several requests to this API using Postman.

**Important Note: ** As is required, we don't need to install any database because the project use H2 database. Also keep in mind that with this kind of DB the registers will be deleted when we stop the application.

### Configuring PostMan 
Open PostMan application and press the button import and select the file which is located under the cloned project restAPI with name RestItemApi.postman_collection.json. This file contains all the requests of the API.

## Testing the endpoints

- **POST request to: localhost:8080/login**

This endpoint provide a token that you will use in some endpoints in order to get authentication and authorization for do the requests. We need to include in the request of  the body  a body json like this:

    {
    "username":"adminUser",
    "password":"adminUser"
    }

Look for a response header in the authorization field and add the token to the endpoints that require autentication and authorization.

- **POST request to: localhost:8080/app/item** 

This endpoint is used for save Items in the database, we need to include in the request of  the body  a body json like this:

      {
            "itemId":1,
            "itemName":"item_x",
            "itemEnteredByUser":"user_x",
            "itemEnteredDate":"2020-05-10T13:00:41.499",
            "itemBuyingPrice":50.0,
            "itemSellingPrice":55.0,
            "itemLastModifiedDate":"2020-05-10T13:00:41.498",
            "itemLastModifiedByUser":"user_y",
            "itemStatus":"AVAILABLE"
    }

This item is securized; only user user with role **'Admin'** can save Items.

For test this endpoint is necessary to send a token in the header of the request; You can get a token for user Admin by calling the endpoint  for get the token (First endpoint of this documentation).

If the **itemId** exists in the database return **http status code 400** while if the **itemId** doesn´t exist it will return **http status code 201**

- **PUT request to localhost:8080/app/item/{itemId} **

This endpoint is used for modify Items in the database, we need to include in the request of  the body  a body json with the fields that we wish to change like this:

    {
            "itemName":"Computadora",
            "itemEnteredByUser":"Carlos",
            "itemEnteredDate":"2020-05-10T13:00:41.499",
            "itemStatus":"SOLD"
    }

This item is securized; only user user with role **'Admin'** can save Items.

For test this endpoint is necessary to send a token in the header of the request; You can get a token for user Admin by calling the endpoint  for get the token (First endpoint of this documentation).

If the **itemId **of the path exists in the database return **https status code 200** while if the **Itemid** doesn´t exist it will return **http status code 404**

- **DELETE request to localhost:8080/app/item/{itemId}:**

This endpoint is used for delete an item with id **itemID**

This item is securized; only user user with role **'Admin'** can save Items.

For test this endpoint is necessary to send a token in the header of the request; You can get a token for user Admin by calling the endpoint  for get the token (First endpoint of this documentation).

If the **id **of the path exists in the database return **https status code 200** while if the **id** doesn´t exist it will return **http status code 400**

- **DELETE request to localhost:8080/app/item:**

This endpoint is used for delete all the items of the database.

This endpoint is securized; only user user with role **'Admin'** can save Items.

This item is securized; only user user with role **'Admin'** can save Items.
For test this endpoint is necessary to send a token in the header of the request; You can get a token for user Admin by calling the endpoint  for get the token (First endpoint of this documentation).

This endpoint return a **http status code 200**

- **GET request to localhost:8080/app/item/{itemId}:**

This endpoint is used for get the item with id **itemId** of the path

If the **itemId **of the path exists in the database return **https status code 200** while if the **itemId** doesn´t exist it will return **http status code 404**

**GET request to localhost:8080/app/item: **

This endpoint is used for get all the items of the database, Take in consideration that if not exist items in the database it will return an empty array.

This endpoint return a **http status code 200**

- **GET request to localhost:8080/app/item?itemStatus={status}&itemEnteredByUser={enteredBy}:**

This endpoint return all the items of the database where itemStatus is **ItemStatus** of the path and itemEnteredByUser is **ItemEnteredByUser** of the path. Take in consideration that if not exist items in the database it will return an empty array.

This endpoint return a **http status code 200**

- **GET request to /app/item?pageSize={pageSize}&page={page}&sortBy={sortByField}:**

This endpoint return  the requested page by paginating with **pageSize** and sorting by the **sortBy** field

This endpoint return a **http status code 200**

