#!/bin/bash

Help()
{
   # Display Help
   echo "Run application Docker"
   echo
   echo "Syntax: run_script.sh [-h|b]"
   echo "options:"
   echo "b   Build application docker before run"
   echo "h   Print this Help."
   echo
}

############################################################
# Process the input options. Add options as needed.        #
############################################################
# Get the options
while getopts "hb" option; do
    case $option in
        h) # display Help
            Help
            exit;;
        b) # Build Application Docker
            ./mvnw clean package
            docker build -t rest_investment .
            ;;
        \?) # Invalid option
            echo "Error: Invalid option "
            Help
            exit;;
    esac
done

# Run Application Docker
docker run -p 8081:8081 rest_investment
