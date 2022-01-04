# SAP Challenge 

[![Java CI with Maven](https://github.com/merkez/sap-ch/actions/workflows/maven.yml/badge.svg)](https://github.com/merkez/sap-ch/actions/workflows/maven.yml)

The challenge is to have a reservation system with a given hotel size by user.
Once a user provided the information about hotel size, then the system is ready to receive
reservations. Each room inside the hotel has reservation list which includes all start day and end day of reservations. 

The system only works with given two main assumptions: 

- All rooms are identical and may be assumed to be numbered. The size of your hotel is size <= 1000.
- Guest do not change the room during their stay, but always stay within the room they moved in initially.

Apart from the given assumptions, input format should be provided as requested from the console.

## How to run 

Running the application is straightforward, main function can be executed directly on any IDE.
For demonstration purposes, I will do it through command line as represented below:

- For demonstration purposes, Test Case #5 is tested.

[![asciicast](https://asciinema.org/a/vV3utSZ66RaRzJrmdfIxcLpXB.svg)](https://asciinema.org/a/vV3utSZ66RaRzJrmdfIxcLpXB)

## Test Cases & CI 

The test cases mentioned on the challenge description is added to [HotelTest.java](./src/test/java/HotelTest.java)

- 1a/1b: Requests outside our planning period are declined (Size=1)
- Requests are accepted (Size=3)
- Requests are declined (Size=3)
- Requests can be accepted after a decline (Size=3)
- Complex Requests (Size=2)

All test cases are running on CI pipeline which is provided under [./github/workflows/maven.yaml](./github/workflows/maven.yaml)
