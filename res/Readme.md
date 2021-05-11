# Project5--Cross-StitchApplication.

### About
This project is the implementation of Project5--Cross-StitchPattern. The implementation does all the functions mentioned in the design doc.

### List of features
* Blur the Image.
* Sharpen the imagemodel.image.
* Color transform the imagemodel.image to Sepia.
* Color transform the imagemodel.image to Grayscale.
* Change color density to a user defined number of channels without dithering.
* Change color density to a user defined number of channels with dithering(with essence).
* Pixelate the image depending in the number of squares per width
* Make mosaic of the image based on the seed value passed.
* Generate the cross-stitch pattern of the image.
* Ability to run the program from a text file of commands(batch file controller).
* GUI to run the code.
* Change floss color of pattern generated in the GUI.
* Display the text pattern along with the generated image.
* Users have option to choose custom DMC colors to generate a pattern with their color choices.
* Delete a pixel from the generated pattern in the GUI.
* Run the batch commands from the GUI. (The same functionality is provided like when we passed it as the command line argument).

### How to Use:
In this iteration of the project there are two ways in which we can run the project. The first way is to process the batch file as seen in the last project
by running the command ```java -jar ImageModel.jar -script path-of-script```, where -script is the command followed by the path to the input script (Make sure to add the full path to the file).***Make sure that the image file is present where the jar is placed***. The
other way we can run this is in the GUI mode. To do this run the command ```java -jar fileName.jar -interactive``` where -interactive is the command which runs
the software in the GUI mode.
To run the program we have to run the executable jar. To run the executable jar we do the following steps.
* If in a windows environment we have two options. We can create a .bat file which runs either of the commands ```java -jar ImageModel.jar -script path-of-script```, or ```java -jar fileName.jar -interactive```. Running the .bat file runs the code.
* This method is environment independent. Open the terminal or the command line and go to(change directory) the jar files directory. Here run the command ```java -jar ImageModel.jar -script path-of-script``` or ```java -jar fileName.jar -interactive```,
to run the program in the GUI mode..

**Note**:
* The images should be present in the ***directory of jar*** when running in script mode or the code will throw an exception.
* The full file name should be provided i.e with the extension. eg. Image1.**jpg**, Image.**png** when loading/saving .etc. when running in script mode.

### Commands for various operations
```
COMMANDS - DESCRIPTION
load fileName - to load an image .e.g. load goat.png
save fileName - save the image e.g. save goat-sharpen.png
blur - blur the image.
sharpen - sharpen the image.
dither channelNumber - dither the image based on the number of channels. e.g. dither 8
reduce channelNumber - color reduce the image based on the number of channels. reduce 8
mosaic seed_value - make a mosaic pattern of the image based on the number of seeds. e.g. mosaic 8
pixelate number_of_squares - pixelate the image based on the number of squares across the width of the image. e.g. pixelate 50
sepia - Color tranform the image to sepia.
grayscale - Color transform the image to grayscale.
pattern - Generate pattern of the image. Image should be pixelated before generating the pattern.
```
### Instructions on running the GUI
* Before running any operation make sure to upload the image to the GUI using the 'Upload Image' button.
* To blur the image go to processing options menu, and click the Blur menu item, in order to blur it.
* To sharpen the image go to processing options menu, and click the Sharpen menu item, in order to sharpen it.
* To color transform the image to sepia go to processing options menu, and click the sepia menu item, in order to color transform image to 'sepia'. 
* To color transform the image to Gray Scale go to processing options menu, and click the Gray Scale menu item, in order to color transform image to 'Gray Scale'.
* To create a mosaic image, click the menu item Mosaic. On doing you'll be prompted to enter a value for the seed size. Make sure to enter a ***positive integer***. Press 'OK' and you'll 
see the processed image in the processed image panel.
* To create a pixelated image, click the menu item Pixelate. On doing you'll be prompted to enter a value for the square size. Make sure to enter a ***positive integer***. Press 'OK' and you'll 
see the processed image in the processed image panel.
* To create a dithered image, click the menu item Dither. On doing you'll be prompted to enter a value for the number of channels. Make sure to enter a ***positive integer***. Press 'OK' and you'll 
see the processed image in the processed image panel.
* To generate a pattern, click the 'Generate Pattern' button. On doing this you'll be prompted to enter the square size for the pixelation.Make sure to enter a ***positive integer***. After pressing
'OK', and the processed image will appear in the processed image panel. On generating a pattern you'll see a new panel appear in the bottom half of the UI. In this panel you can see a few things.
The ***legend*** of the pattern generated. The option to delete a pixel, replace a pixel color with another **DMC** floss color of your choice. To **delete** a pixel, click on the pixel color in the image.
This will give you a prompt on the color selected and its RGB constituents. Now to delete the pixel press the delete pixel button, you'll see the updated legend in the legend list. To **replace** a pixel 
click the pixel in the processed image list which will give you a prompt on the color selected. Now choose a replacement color from the 'Replacement DMC Values' list by clicking on it. Now, press the replace 
button and voila, your pixel is updated on the image and the legend.
* After the above steps you can save the pattern on a file as well. For doing that just press the "Save Pattern Text" button.
* To generate a pattern from your **custom color choices**, first press the 'Generate Pattern With Custom DMC Colors'. On doing this a frame pops up which prompts you to add the colors of your choice for pattern
generation. To choose a color click on a color click on the 'DMC Color Pane' list and you'll see the 'Color chosen' list populate. To remove a color from the color chosen list, just click on the color entry in the
color chosen list. Now, to proceed press the 'Process Image' button, and viola, a pattern is generated with your custom colors. On generating a pattern you'll see a new panel appear in the bottom half of the UI. In this panel you can see a few things.
The ***legend*** of the pattern generated. The option to delete a pixel, replace a pixel color with another **DMC** floss color of your choice. To **delete** a pixel, click on the pixel color in the image.
This will give you a prompt on the color selected and its RGB constituents. Now to delete the pixel press the delete pixel button, you'll see the updated legend in the legend list. To **replace** a pixel 
click the pixel in the processed image list which will give you a prompt on the color selected. Now choose a replacement color from the 'Replacement DMC Values' list by clicking on it. Now, press the replace 
button and voila, your pixel is updated on the image and the legend.
* To write a 'batch' like command, click the 'Batch Processing' tab. Now put the commands in the text area as stated in the above command list. Press the 'Process Batch' button to run the batch file. 
### Description of Runs:
Running the program with the following input.txt file

```
Input.txt : 
load goat.png
dither 8
save goat-dither.png
load goat.png
blur
sepia
save goat-blur.png
load goat.png
pixelate 100
pattern
save goat-pattern-pixelated.txt
```
And the result generated : 
```
Image loaded Image dithered Image saved. 
Image loaded Image blurred Color Transformed to Sepia Image saved. 
Image loaded Image pixelated Cross-stitch pattern generatedWritten to file.
The program has processed your images.
Press f to exit.
f
```

### Design Changes:
A few changes we're made in the design of this project. Addition of a few panels and frames when compared to the original design. Addition of the Color Selection Dialog class for 
custom pattern generation. Addition of a list renderer class for rendering lists. Added a panel for all the pattern generation operations(Only visible on pattern generation). A pattern panel
for printing the symbols of the pattern generated. A few methods we're added to the feature interface to accommodate all the requirements for the project. Addition of methods like deletePixel(),
reducePixel(), and patternGenerateCustomColor, to generate pattern with custom colors.

### Assumptions
#### Batch Mode
* The user will pixelate the image before pattern generation.
* The user will use the correct commands.
* The user will load images before running them.
* The user will provide the name of the new file to be stored with the appropriate extension.

### GUI--Interactive mode
* The user will upload a proper image file.
* The user will wait for the operations to complete.

### Limitations:
#### Batch mode
* As of now the code provides only two color transformations - Sepia and GrayScale.
* As of now the code provides only two types of filtering - Blur and Sharpen.
* Before generating the pattern the image must be pixelated to get the result.
* The user is expected to load the image before conducting any operations.
* It's the users responsibility to save the image.
* Users are expected to enter the commands as specified in this document.
* Users are expected to pixelate the image before generating the cross-stitch pattern.
### GUI--Interactive mode
* The user doesn't have the facility to write cross-stitch words into the image
## License:
The photo used in this project are mine and I authorize its usage.
