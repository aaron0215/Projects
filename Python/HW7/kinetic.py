#Aaron Zhang
#CS021 Green group
#This is function to calculate kinetic energy
#Use two functions to receive date and calculate
#Use exception statement to except invalid values
def main():
        try:
                mass_int = int(input("Enter the object's mass in kilograms: "))
                velocity_int = int(input("Enter the object's velocity in meters per second: "))
                ke = kinetic_energy(mass_int,velocity_int)
        except ValueError:
                print("The values must be valid")
                mass_int = int(input("Enter valid object's mass in kilograms: "))
                velocity_int = int(input("Enter valid foobject's velocity in meters per second: "))
                ke = kinetic_energy(mass_int,velocity_int)
        finally:
                print('The Kinetic Energy is',format(ke,'.2f'))
def kinetic_energy(mass, velo):
        k_energy = 1/2*mass*velo
        return k_energy
main()    
