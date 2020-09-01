// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package facedetection.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;

public class Microflows
{
	// These are the microflows for the FaceDetection module
	public static void aCT_DeletePhoto(IContext context, facedetection.proxies.Photo _photo)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		params.put("Photo", _photo == null ? null : _photo.getMendixObject());
		Core.microflowCall("FaceDetection.ACT_DeletePhoto").withParams(params).execute(context);
	}
	public static void aCT_ProcessImage(IContext context, facedetection.proxies.Photo _photo)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		params.put("Photo", _photo == null ? null : _photo.getMendixObject());
		Core.microflowCall("FaceDetection.ACT_ProcessImage").withParams(params).execute(context);
	}
}