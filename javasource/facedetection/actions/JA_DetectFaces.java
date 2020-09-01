// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package facedetection.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.BoundingPoly;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import facedetection.proxies.FaceLocation;

public class JA_DetectFaces extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private IMendixObject __SourceImage;
	private facedetection.proxies.Photo SourceImage;
	private IMendixObject __CredentialsFile;
	private facedetection.proxies.Credential CredentialsFile;

	public JA_DetectFaces(IContext context, IMendixObject SourceImage, IMendixObject CredentialsFile)
	{
		super(context);
		this.__SourceImage = SourceImage;
		this.__CredentialsFile = CredentialsFile;
	}

	@java.lang.Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		this.SourceImage = __SourceImage == null ? null : facedetection.proxies.Photo.initialize(getContext(), __SourceImage);

		this.CredentialsFile = __CredentialsFile == null ? null : facedetection.proxies.Credential.initialize(getContext(), __CredentialsFile);

		// BEGIN USER CODE

		// Setup credentials for Google Vision
		IContext ctx = getContext();
		InputStream credentialsFIle = Core.getFileDocumentContent(ctx, __CredentialsFile);
		CredentialProvider provider = new CredentialProvider(credentialsFIle);
		ImageAnnotatorSettings imageAnnotatorSettings =
				ImageAnnotatorSettings.newBuilder()
				.setCredentialsProvider(provider)
				.build();
		
		// Convert photo into format useable for Google Vision
		InputStream fis =  Core.getFileDocumentContent(ctx, __SourceImage);
		ByteString imgBytes = ByteString.readFrom(fis);
		Image img = Image.newBuilder().setContent(imgBytes).build();
		
		// Set the feature type to Facial Detection
		Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
		
		// Prepare the request for the API
		AnnotateImageRequest request =
				AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		List<AnnotateImageRequest> requests = new ArrayList<>();
		requests.add(request);

		// Handle responses
		ArrayList<IMendixObject> faceLocations = new ArrayList<IMendixObject>();
		try (ImageAnnotatorClient client = ImageAnnotatorClient.create(imageAnnotatorSettings)) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();
			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					return faceLocations;
				}
				
				// For each face gather information needed
				for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
					BoundingPoly boundingBox = annotation.getBoundingPoly();
					List<com.google.cloud.vision.v1.Vertex> boxVertices = boundingBox.getVerticesList();
					
					// Gather X,Y, Width & Height
					int X = Math.round(boxVertices.get(0).getX());
					int Y = Math.round(boxVertices.get(0).getY());
					int Width = Math.round(boxVertices.get(2).getX()) - X;
					int Height = Math.round(boxVertices.get(2).getY()) - Y;
					
					// Set FaceLocation Attributes
					FaceLocation faceLocation = new FaceLocation(ctx);
					faceLocation.setX(X);
					faceLocation.setY(Y);
					faceLocation.setWidth(Width);
					faceLocation.setHeight(Height);
					
					// Add new location to List
					faceLocations.add(faceLocation.getMendixObject());
				}
			}
		}
		return faceLocations;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "JA_DetectFaces";
	}

	// BEGIN EXTRA CODE
	public class CredentialProvider implements CredentialsProvider {

		private InputStream authStream;
		private boolean usePath=false;
		private String path;

		public CredentialProvider(InputStream inputStream) {
			this.authStream = inputStream;
			this.usePath=false;
		}

		public CredentialProvider(String inputPath) {
			this.path = inputPath;
			this.usePath=true;
		}

		@Override
		public Credentials getCredentials() throws IOException {
			// TODO Auto-generated method stub
			if (this.usePath)
			{
				return GoogleCredentials.fromStream(new FileInputStream(path))
						.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
			}
			else
			{
				return GoogleCredentials.fromStream(authStream)
						.createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
			}
		}

	}
	// END EXTRA CODE
}
