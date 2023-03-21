# Java Wrapper for SignalWire Compatibility APIs

An Unofficial Java API wrapper that allows for the rapid development and integration of Java-based applications with the SignalWire
API.

> ⚠️ Disclaimer:
>
> The libraries in this repository are NOT supported by SignalWire. SignalWire will _not_ answer support questions about the code in this repository, and is not responsible for fixing issues.

This library automates the process of consuming the Signalwire API by providing a wide range of helper methods that
allow for quick development and testing.

## Documentation

The documentation for the SignalWire API can be
found [here](https://developer.signalwire.com/compatibility-api/reference/overview)

## Getting Started

### Dependencies

```xml
<dependency>
    <groupId>io.github.signalwire-community</groupId>
    <artifactId>compatibility-api-java</artifactId>
    <version>1.1.0</version>
</dependency>
```
## Usage

Initialize `SignalWireClient`

```java
SignalWireClient client = new SignalWireClient(projectId,apiToken,spaceName);
```

This class give you access to the `voice`, `message`, `accounts`, `phonenumber` repository

Get your project Id and API token from your signalWire space. for more information
click [here](https://developer.signalwire.com/apis/docs/navigating-your-space)

### Sending a Message

```java
NewMessageResponse response = client.message().create(to,from,body);
```

The above Line takes the `to`, `from` and `body` parameter to send an email.

### Making a Phone call

```java
Call response = client.voice().create(from,url,to,record,statusCallback);
```

The above statement returns the call object which includes all information about the placed call

### List of Accounts

```java
AccountResponse response = client.accounts().getAccounts();
```

Returns the list of accounts within the project space

### Create an XML bin

The Library consist f a helper method that generates an xml string that can be used to create an XML bin on SignalWire

```java
// Generate the XML String    
VoiceResponse response = new VoiceResponse.Builder()
        .say(null,"Hello world")
        .say(null,"Welcome to the unofficial SW java library")
        .build();

String xml = response.getResponse(); // This returns the content in raw xml file

// Create an XML bin on SignalWire
Bin bin = client.xml().create("new_bin_title",xml)
```

### Create a Sip endpoint
You can create a SIP endpoint using the below snippet

```java
List<String> ciphers = new ArrayList<>();
ciphers.add("AEAD_AES_256_GCM_8");
ciphers.add("AES_256_CM_HMAC_SHA1_80");
ciphers.add("AES_CM_128_HMAC_SHA1_80");
ciphers.add("AES_256_CM_HMAC_SHA1_32");
ciphers.add("AES_CM_128_HMAC_SHA1_32");

List<String > codecs = new ArrayList<>();
codecs.add("OPUS");
codecs.add("G722");
codecs.add("PCMU");
codecs.add("PCMA");
codecs.add("VP8");
codecs.add("H264");
SipResponse.Sip create = client.sip().create("testing-sip","password","testing", "+1444433344",ciphers, codecs,"required")
```