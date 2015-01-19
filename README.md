# http-client-interfaces

Abstract HTTP client interface definitions.

## Purpose

This library provides an abstract HTTP interface for libraries that implement HTTP-based protocols or clients of HTTP-based APIs without having to pull in the dependencies of a specific HTTP client implementation and leaving the choice of the implementation to the project that uses the library.

This approach allows to use different HTTP implementations without changing the library. Also it takes the burden from the library to configure details like authentication, SSL, proxies or compression or to provide an interface to configure these, allowing the library developer to focus on the actual protocol.

Note, this does not intend to be a full featured HTTP client library. It's kind of a minimal interface of an HTTP client and a few helper methods.

## Example

Using these interfaces a simple `GET` request to receive a JSON file is described like so

```java
public class GetJsonRequest implements IHttpRequest<JSONObject>
{

	private static final ContentType RESPONSE_CONTENT_TYPE = new ContentType("application/json");

	private final JsonObjectResponseHandler mResponseHandler = new JsonObjectResponseHandler();


	@Override
	public HttpMethod getMethod()
	{
		// use a GET request
		return HttpMethod.GET;
	}


	@Override
	public void updateHeaders(IHeaderEditor headerEditor)
	{
		// don't need any headers
	}


	@Override
	public IHttpRequestEntity getRequestEntity()
	{
		// this is a GET request, no need for a request entity
		return null;
	}


	@Override
	public IResponseHandler<JSONObject> getResponseHandler(IHttpResponse response) throws ProtocolException
	{
		if (response.getStatusCode() == HttpStatus.OK)
		{
			IHttpResponseEntity entity = response.getContentEntity();
			if (!Utils.verifyContentType(entity, RESPONSE_CONTENT_TYPE))
			{
				Utils.consumeEntitySilently(entity);
				throw new ProtocolException("invalid content type: " + entity.getContentType());
			}

			return mResponseHandler;
		}
		// we don't handle this, the executor will take care of throwing an execption
		return null;
	}
}
```

To send this request all you need an instance that implements `IHttpRequestExecutor` (in this example it's called `mExecutor`):

```
	JSONObject  result = null;
	try
	{
		result = mExecutor.execute(url, new GetJsonRequest(), FollowSecureRedirectionCallback.INSTANCE);
	}
	catch (RedirectionException e)
	{
		// do some error handling
	}
	catch (ProtocolError e)
	{
		// do some error handling
	}
	catch (ProtocolException e)
	{
		// do some error handling
	}
	catch (UnhandledStatusError e)
	{
		// do some error handling
	}
	catch (IOException e)
	{
		// do some error handling
	}

```

## License

Copyright (c) Marten Gajda 2014, licensed under Apache2.


