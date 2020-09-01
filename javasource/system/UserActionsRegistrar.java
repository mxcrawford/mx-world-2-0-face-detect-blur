package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(facedetection.actions.JA_BlurBoxes.class);
    registrator.registerUserAction(facedetection.actions.JA_DetectFaces.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
