import numpy as np
import pandas as pd
import requests
import json


class Api(object):
    def __init__(self, USER_KEY, host="https://developers.zomato.com/api/v2.1",
                 content_type='application/json'):
        self.host = host
        self.user_key = USER_KEY
        self.headers = {
            "User-agent": "curl/7.43.0",
            'Accept': content_type,
            'X-Zomato-API-Key': self.user_key
        }

    def get(self, endpoint, params):
        url = self.host + endpoint + "?"
        for k,v in params.items():
            url = url + "{}={}&".format(k, v)
        url = url.rstrip("&")
        response = requests.get(url, headers=self.headers)
        return response.json()


class Pyzomato(object):
    def __init__(self, USER_KEY):
        self.api = Api(USER_KEY)

    def getCategories(self):
        """
        Get a list of categories. List of all restaurants categorized under a particular restaurant
        type can be obtained using /Search API with Category ID as inputs
        """
        categories = self.api.get("/categories", {})
        return categories

    def getCityDetails(self, **kwargs):
        """
        :param q: query by city name
        :param lat: latitude
        :param lon: longitude
        :param city_ids: comma separated city_id values
        :param count: number of max results to display

        Find the Zomato ID and other details for a city . You can obtain the Zomato City ID in one of the following ways:
            -City Name in the Search Query - Returns list of cities matching the query
            -Using coordinates - Identifies the city details based on the coordinates of any location inside a city
        If you already know the Zomato City ID, this API can be used to get other details of the city.
        """
        params = {}
        available_keys = ["q", "lat", "lon", "city_ids", "count"]
        for key in available_keys:
            if key in kwargs:
                params[key] = kwargs[key]
        cities = self.api.get("/cities", params)
        return cities

    def getCollectionsViaCityId(self, city_id, **kwargs):
        """
        :param city_id: id of the city for which collections are needed
        :param lat: latitude
        :param lon: longitude
        :param count: number of max results to display
        Returns Zomato Restaurant Collections in a City. The location/City input can be provided in the following ways
         - Using Zomato City ID
         - Using coordinates of any location within a city
         - List of all restaurants listed in any particular Zomato Collection can be obtained using the '/search' API with Collection ID and Zomato City ID as the input
        """
        params = {"city_id": city_id}
        optional_params = ["lat", "lon", "count"]

        for key in optional_params:
            if key in kwargs:
                params[key] = kwargs[key]
        collections = self.api.get("/collections", params)
        return collections

    def getCuisines(self, city_id, **kwargs):
        """

        :param city_id: id of the city for which collections are needed
        :param lat: latitude
        :param lon: longitude
        Get a list of all cuisines of restaurants listed in a city.
        The location/city input can be provided in the following ways
          - Using Zomato City ID
          - Using coordinates of any location within a city
        List of all restaurants serving a particular cuisine can be obtained using
        '/search' API with cuisine ID and location details
        """
        params = {"city_id": city_id}
        optional_params = ["lat", "lon"]

        for key in optional_params:
            if key in kwargs:
                params[key] = kwargs[key]
        cuisines = self.api.get("/cuisines", params)
        return cuisines

    def getEstablishments(self, city_id, **kwargs):
        """
        :param city_id: id of the city for which collections are needed
        :param lat: latitude
        :param lon: longitude
        Get a list of restaurant types in a city. The location/City input can be provided in the following ways
        - Using Zomato City ID
        - Using coordinates of any location within a city
        List of all restaurants categorized under a particular restaurant type can obtained using
        /Search API with Establishment ID and location details as inputs
        """
        params = {"city_id": city_id}
        optional_params = ["lat", "lon"]

        for key in optional_params:
            if key in kwargs:
                params[key] = kwargs[key]
        establishments = self.api.get("/establishments", params)
        return establishments

    def getByGeocode(self, lan, lon):
        """
        :param lan: latitude
        :param lon: longitude
        Get Foodie and Nightlife Index, list of popular cuisines and nearby restaurants around the given coordinates
        """
        params = {"lat": lan, "lon": lon}
        response = self.api.get("/geocode", params)
        return response

    def getLocationDetails(self, entity_id, entity_type):
        """
        :param entity_id: location id obtained from locations api
        :param entity_type: location type obtained from locations api
        :return:
        Get Foodie Index, Nightlife Index, Top Cuisines and Best rated restaurants in a given location
        """
        params = {"entity_id": entity_id, "entity_type": entity_type}
        location_details = self.api.get("/location_details", params)
        return location_details

    def getLocations(self, query, **kwargs):
        """
        :param query: suggestion for location name
        :param lat: latitude
        :param lon: longitude
        :param count: number of max results to display
        :return: json response
        Search for Zomato locations by keyword. Provide coordinates to get better search results
        """
        params = {"query": query}
        optional_params = ["lat", "lon", "count"]

        for key in optional_params:
            if key in kwargs:
                params[key] = kwargs[key]
        locations = self.api.get("/locations", params)
        return locations

    def getDailyMenu(self, restaurant_id):
        """
        :param restaurant_id: id of restaurant whose details are requested
        :return: json response
        Get daily menu using Zomato restaurant ID.
        """
        params = {"res_id": restaurant_id}
        daily_menu = self.api.get("/dailymenu", params)
        return daily_menu

    def getRestaurantDetails(self, restaurant_id):
        """
        :param restaurant_id: id of restaurant whose details are requested
        :return: json response
        Get detailed restaurant information using Zomato restaurant ID.
        Partner Access is required to access photos and reviews.
        """
        params = {"res_id": restaurant_id}
        restaurant_details = self.api.get("/restaurant", params)
        return restaurant_details

    def getRestaurantReviews(self, restaurant_id, **kwargs):
        """
        :param restaurant_id: id of restaurant whose details are requested
        :param start: fetch results after this offset
        :param count: max number of results to retrieve
        :return: json response
        Get restaurant reviews using the Zomato restaurant ID
        """
        params = {"res_id": restaurant_id}
        optional_params = ["start", "count"]

        for key in optional_params:
            if key in kwargs:
                params[key] = kwargs[key]
        reviews = self.api.get("/reviews", params)
        return reviews

    def search(self, **kwargs):
        """
        :param entity_id: location id
        :param entity_type: location type (city, subzone, zone, lanmark, metro , group)
        :param q: search keyword
        :param start: fetch results after offset
        :param count: max number of results to display
        :param lat: latitude
        :param lon: longitude
        :param radius: radius around (lat,lon); to define search area, defined in meters(M)
        :param cuisines: list of cuisine id's separated by comma
        :param establishment_type: estblishment id obtained from establishments call
        :param collection_id: collection id obtained from collections call
        :param category: category ids obtained from categories call
        :param sort: sort restaurants by (cost, rating, real_distance)
        :param order: used with 'sort' parameter to define ascending / descending
        :return: json response
        The location input can be specified using Zomato location ID or coordinates. Cuisine / Establishment /
        Collection IDs can be obtained from respective api calls.

        Partner Access is required to access photos and reviews.

        Examples:
        - To search for 'Italian' restaurants in 'Manhattan, New York City',
        set cuisines = 55, entity_id = 94741 and entity_type = zone
        - To search for 'cafes' in 'Manhattan, New York City',
        set establishment_type = 1, entity_type = zone and entity_id = 94741
        - Get list of all restaurants in 'Trending this Week' collection in 'New York City' by using
        entity_id = 280, entity_type = city and collection_id = 1
        """
        params = {}
        available_params = [
            "entity_id", "entity_type", "q", "start",
            "count", "lat", "lon", "radius", "cuisines",
            "establishment_type", "collection_id",
            "category", "sort", "order"]

        for key in available_params:
            if key in kwargs:
                params[key] = kwargs[key]
        results = self.api.get("/search", params)
        return results

bot_number = '248-313-8557'

lat = input('lat')
lon = input('lon')
p = Pyzomato("aeaba48aeeb0718290136c41274b13fe")

nearby_res = p.getByGeocode(lat, lon)['popularity']['nearby_res']

col = ['id', 'name', 'address', 'phone_number', 'cuisines', 'price_range', 'rating', 'photo_url', 'menu_url']
df = pd.DataFrame(columns = col)

# get desired data from all restaurants in nearby area
for i in nearby_res:
    rest_data = p.getRestaurantDetails(i) # JSON data of current restaurant
    id = rest_data['id']
    name = rest_data['name']
    address = rest_data['location']['address']
    phone_number = bot_number
    cuisines = rest_data['cuisines']
    price_range = rest_data['price_range']
    rating = rest_data['user_rating']['aggregate_rating']
    photo_url = rest_data['photos_url']
    menu_url = rest_data['menu_url']
    
    df2 = pd.DataFrame(data=[[id,name,address,phone_number,cuisines,price_range,rating,photo_url,menu_url]], columns = col)
    df = df.append(df2, ignore_index = True)


import gspread
from oauth2client.service_account import ServiceAccountCredentials
import random

scope = ['https://spreadsheets.google.com/feeds', 'https://www.googleapis.com/auth/drive']
creds = ServiceAccountCredentials.from_json_keyfile_name('client_secret.json', scope)
client = gspread.authorize(creds)

sheet = client.open('picked_restaurants').sheet1

rests = sheet.get_all_records()

print(rests)

import gspread
from oauth2client.service_account import ServiceAccountCredentials

scope = ['https://spreadsheets.google.com/feeds', 'https://www.googleapis.com/auth/drive']
creds = ServiceAccountCredentials.from_json_keyfile_name('client_secret.json', scope)
client = gspread.authorize(creds)

sheet = client.open('picked_restaurants').sheet1

n = random.randrange(len(df))

sheet.update_cell(2, 1, df['name'][n])
sheet.update_cell(2, 2, df['address'][n])
sheet.update_cell(2, 3, df['photo_url'][n])
sheet.update_cell(2, 4, df['menu_url'][n])
sheet.update_cell(2, 5, df['phone_number'][n])
sheet.update_cell(2, 6, df['price_range'][n])
sheet.update_cell(2, 7, df['rating'][n])
sheet.update_cell(2, 8, df['cuisines'][n])

rests = sheet.get_all_records()

print(rests)
