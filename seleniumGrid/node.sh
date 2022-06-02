#!/bin/bash
java -jar selenium-server-standalone-2.44.0.jar -role node -nodeConfig nodeconfig.json -Dwebdriver.chrome.driver="..\drivers\102\chromedriver_mac64\chromedriver"
sleep 10000