pragma solidity ^0.4.0;

contract PatientMaster{
    address private owner;
    address private HealthCaretaker;
    address private Insurance;
    address private DeviceSupplier;
    uint private weight;
    uint private Dosage;
    string private serialNumber;
    bool private engineState = true;
    bool private power = false;
    address private FDA;
    string private location = "Buffalo, New York";
    string private expDate = "4/15/2018";
    uint private oldWeight;
    uint private curWeight;
    uint private amount;
    
    function PatientMaster(address h, address i, address d, string id) public {  // patients set their doctor, insurance, and deviceDistribute
        owner = msg.sender;
        HealthCaretaker = h;
        Insurance = i;
        DeviceSupplier = d;
        serialNumber = id;
    }
    
    modifier OwnerPatient{                                                          //Patient controll modifier
        require(owner == msg.sender);
        _;
    }
    modifier FDAview {                  //FDAview limited view
        require(FDA == msg.sender);
        _;
    }
    modifier PatientHealthcareView {        //
        require((owner == msg.sender) || (HealthCaretaker == msg.sender));
        _;
    }
    modifier PatientAuthorizationHealth(){
        require(HealthCaretaker == msg.sender);
        _;
    }
    
    modifier PatientAuthorizationIns(){
        require(Insurance == msg.sender);
        _;
    }
    
    modifier DeviceSupplierAuthorization(){         // set manufacturer 
        require(DeviceSupplier == msg.sender);
        _;
    }
    
    
    function setHealthMod(address health) OwnerPatient public{  //set the clinic or primary physician that can prescribe dosage.
        HealthCaretaker = health;
    }
    
    function setInsurance(address insure) OwnerPatient public{  //set the insurance so they can obtain your information easier for lower cost. (less insurance IT staffs)
        Insurance = insure;
    }
    
    function setDeviceS(address devs) OwnerPatient public{
        DeviceSupplier = devs;
    }
    
    function checkWeight() PatientHealthcareView public view returns (uint){
            return curWeight;
    } 
    
    function recordWeight(uint w) PatientAuthorizationHealth public {
        oldWeight = curWeight;
        curWeight = w;
        //assuming that the device records once a day.
    }
    function weightEvaluation() PatientAuthorizationHealth public view returns (bool) {   // for doctor to see if the treatment is working.
        if (curWeight >= oldWeight) {
             return true;
        }
    }
    
    function getDosage() PatientAuthorizationHealth public view returns (uint _dosage, string _unit){
        _dosage = Dosage;
        _unit = "ml/hr";
        return (_dosage, _unit);
    }
    
    function setDosage(uint newDose) PatientAuthorizationHealth public{
        Dosage = newDose;
    }
  
    function deviceErrors() DeviceSupplierAuthorization public view returns (bool error, string _serial) {
        if(engineState == false || power == false) {
            error = true;
            _serial = serialNumber;
            return (error, _serial);
        } else {
            return (false, _serial);
        }
        
    }
    function getFoodInfo() FDAview public view returns (string, string){
        return (location, expDate);
    }

    
    function sendFoodRequest(string mess, address man, uint256 amt) PatientAuthorizationHealth public {
        location = mess;
        Insurance = man;
        amount = amt;
    }
    
}
