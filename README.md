# http-client-interfaces

Abstract HTTP client interface definitions.

## Purpose

This library provides an abstract HTTP interface for libraries that implement HTTP-based protocols or clients of HTTP-based APIs without having to pull in the dependencies of a specific HTTP client implementation and leaving the choice of the implementation to the project that uses the library.

This approach allows to use different HTTP implementations without changing the library. Also it takes the burden from the library to configure details like authentication, SSL, proxies or compression or to provide an interface to configure these, allowing the library developer to focus on the actual protocol.

Note, this does not intend to be a full featured HTTP client library. It's kind of a minimal interface of an HTTP client and a few helper methods.

## Requirements

This library has no special requirements.

## License

Copyright (c) Marten Gajda 2014, licensed under Apache2.


