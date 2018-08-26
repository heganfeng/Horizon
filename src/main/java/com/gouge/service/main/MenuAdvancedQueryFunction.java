package com.gouge.service.main;

import com.gouge.service.ButtonService;
import com.gouge.service.MenuFunciton;

/**
 * Created by Godden
 * Datetime : 2018/8/8 13:03.
 */
public class MenuAdvancedQueryFunction implements ButtonService{
        private MenuFunciton menuFunciton;
        @Override
        public void execute() {
                menuAdvancedQuery();
        }

        public  MenuAdvancedQueryFunction (MenuFunciton menuFunciton){

        }

        public void menuAdvancedQuery(){

        }
}
