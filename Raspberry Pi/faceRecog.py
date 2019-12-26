import RPi.GPIO as GPIO
import cv2
import numpy as np
import os
import time
import smtplib
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from picamera import PiCamera

# Motion detection from GPIO
def motionDetect():
    pin = 7
    motion = False
    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BOARD)
    GPIO.setup(pin,GPIO.IN)
    print("Motion sensor installed")
    while not motion:
        if GPIO.input(pin):
            print("Motion detected")
            motion = True

    return motion

# Send email while necessary
def sendEmail(imageName):
    img_data = open('/home/pi/Desktop/'+imageName,'rb').read()
    msg = MIMEMultipart()
    msg['Subject'] = "Warning"
    # Sender
    msg['From'] = "zhiyi215@gmail.com"
    # Receiver
    msg['To'] = "zhiyi215@gmail.com"
    text = MIMEText("Warning")
    msg.attach(text)
    image = MIMEImage(img_data, name=os.path.basename(imageName))
    # Add attachment
    msg.attach(image)

    s = smtplib.SMTP("smtp.gmail.com") # If it doesn't work, use next line
    #s = smtplib.SMTP("smtp.gmail.com",587)
    s.ehlo()
    s.starttls()
    s.ehlo()
    s.login("zhiyi215@gmail.com","zzy19960402") # Email account of sender
    #               sender                 receiver             message
    s.sendmail("zhiyi215@gmail.com",["zhiyi215@gmail.com"], msg.as_string())
    s.quit()

# Facial recognition
def RecogFace():
    detected = False

    # built-in functions
    recognizer = cv2.face.LBPHFaceRecognizer_create()
    recognizer.read('trainer/trainer.yml')
    haarcascadesPath = "/usr/local/share/OpenCV/haarcascades/haarcascade_frontalface_default.xml"
    faceCascade = cv2.CascadeClassifier(haarcascadesPath);
    font = cv2.FONT_HERSHEY_SIMPLEX

    #initialize id index
    id = 0

    # names related to ids: example ==> Aaron: id=1
    names = ['Test', 'Aaron', 'Dongzhe'] 

    # Initialize video window
    cam = cv2.VideoCapture(0)
    cam.set(3, 640)
    cam.set(4, 480)

    # Min size to be recognized as face
    minW = 0.1*cam.get(3)
    minH = 0.1*cam.get(4)

    # Count seconds
    start_time = time.time()
    elapsed_time = 0

    # The recognition process would last 30s if there is no unfamilar face detected
    while (elapsed_time < 30 and detected == False):
        ret, img =cam.read()
        gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
        
        # Built-in function
        # Scale is from a tutorial about OpenCV
        faces = faceCascade.detectMultiScale(
            gray,
            scaleFactor = 1.2,
            minNeighbors = 5,
            minSize = (int(minW), int(minH)),
           )

        # Same as faceDataset.py
        for(x,y,w,h) in faces:

            cv2.rectangle(img, (x,y), (x+w,y+h), (0,255,0), 2)

            id, confidence = recognizer.predict(gray[y:y+h,x:x+w])

            # Confidence of familiar face
            if (confidence < 100 and confidence > 80):
                id = names[id]
                print(id)
                confidence = "  {0}%".format(round(100 - confidence))
            else:
                detected = True
                id = "unknown"
                confidence = "  {0}%".format(round(100 - confidence))

            # Difference from faceDataset.py is here
            # Put text about name and confidence around rectangle
            # Built-function: cv2.putText
            cv2.putText(img, str(id), (x+5,y-5), font, 1, (255,0,255), 2)
            cv2.putText(img, str(confidence), (x+5,y+h-5), font, 1, (0,0,255), 2)  

        # Shows video. Not really needed for real use
        cv2.imshow('camera',img) 

        # Count seconds
        elapsed_time = time.time() - start_time

        # Press 'ESC' to exist
        keyIn = cv2.waitKey(10) & 0xff
        if (keyIn == 27 or elapsed_time > 30 or detected == True):
            break

    # Shut down camera
    cam.release()
    cv2.destroyAllWindows()
    
    return detected
    

def main():
    #k = cv2.waitKey(10) & 0xff
    #while (True or k!= 27):
    detected = motionDetect()
    if(detected):
        unfamiliar = RecogFace()
        if(unfamiliar):
            # Activate camera again to capture image
            camera = PiCamera()
            camera.start_preview()
            # Create image, name it with local time
            imageName = str(time.asctime(time.localtime(time.time())))+'.jpg'
            camera.capture('/home/pi/Desktop/'+ imageName)
            camera.stop_preview()
            print("Unknow face detected") # For testing
            # Send email
            sendEmail(imageName)
        else:
            pass
        #time.sleep(5)
    else:
        pass
        #k = cv2.waitKey(10) & 0xff
    #if(key == 27):
        #print("Program ends")

main()
