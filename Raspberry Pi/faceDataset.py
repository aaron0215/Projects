import cv2
import os

# Set the size of frame window
cam = cv2.VideoCapture(0)
cam.set(3, 640) # This scale is commonly used
cam.set(4, 480)

# Built-in detector function
face_detector = cv2.CascadeClassifier('/usr/local/share/OpenCV/haarcascades/haarcascade_frontalface_default.xml')

# User's ID refers to the index of name in the list in recognition program
face_id = input('\n enter user id end press ==>  ')

print("\n Look at the camera and wait. Move heads.")
count = 0

while(True):

    # Built-in function to read from camera
    ret, img = cam.read()
    # Gray-scale image
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # Open source code: commonly used scale
    faces = face_detector.detectMultiScale(gray, 1.3, 5)

    # x,y: the top left corner point of rectangle
    # w,h: width and length of rectangle
    for (x,y,w,h) in faces:

        # Built-in function: initialize rectangle around face
        #           camera  point    size      color   2px
        cv2.rectangle(img, (x,y), (x+w,y+h), (255,0,0), 2)     
        count += 1

        # Save the captured image into database
        cv2.imwrite("dataset/User." + str(face_id) + '.' + str(count) + ".jpg", gray[y:y+h,x:x+w])

        # Show what camera captures
        cv2.imshow('image', img)

    k = cv2.waitKey(100) & 0xff # Press 'ESC' to exit
    if k == 27:
        break
    elif count >= 30: # Capture 30 images as data
         break

print("\n Exit Program")
cam.release()
cv2.destroyAllWindows()
