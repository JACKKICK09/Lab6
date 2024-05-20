package Commands;

import NetWork.Response;

import Objects.ProductModel;


public interface Command{
    Response execute(String command, ProductModel productModel);
}