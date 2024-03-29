/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lqactiviti701.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Tijs Rademakers
 */
@RestController
public class ModelSaveRestResource implements ModelDataJsonConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;
  
//  @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
//  @ResponseStatus(value = HttpStatus.OK)
//  public void saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {
//    try {
//
//      Model model = repositoryService.getModel(modelId);
//
//      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
//
//      modelJson.put(MODEL_NAME, values.getFirst("name"));
//      modelJson.put(MODEL_DESCRIPTION, values.getFirst("description"));
//      model.setMetaInfo(modelJson.toString());
//      model.setName(values.getFirst("name"));
//
//      repositoryService.saveModel(model);
//
//      repositoryService.addModelEditorSource(model.getId(), values.getFirst("json_xml").getBytes("utf-8"));
//
//      InputStream svgStream = new ByteArrayInputStream(values.getFirst("svg_xml").getBytes("utf-8"));
//      TranscoderInput input = new TranscoderInput(svgStream);
//
//      PNGTranscoder transcoder = new PNGTranscoder();
//      // Setup output
//      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//      TranscoderOutput output = new TranscoderOutput(outStream);
//
//      // Do the transformation
//      transcoder.transcode(input, output);
//      final byte[] result = outStream.toByteArray();
//      repositoryService.addModelEditorSourceExtra(model.getId(), result);
//      outStream.close();
//
//    } catch (Exception e) {
//      LOGGER.error("Error saving model", e);
//      throw new ActivitiException("Error saving model", e);
//    }
//  }
  /**
   * 保存流程定义数据
   */

  @PutMapping(value = "/model/{modelId}/save")
  public void saveModel2(@PathVariable String modelId, @RequestParam("name") String name, @RequestParam("json_xml") String json_xml,
                        @RequestParam("svg_xml") String svg_xml, @RequestParam("description") String description) {
    try {
      Model model = repositoryService.getModel(modelId);

      ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

      modelJson.put("model_name", name);
      modelJson.put("model_description", description);
      model.setMetaInfo(modelJson.toString());
      model.setName(name);
      model.setVersion(model.getVersion() + 1);
      repositoryService.saveModel(model);

      repositoryService.addModelEditorSource(model.getId(), Objects.requireNonNull(json_xml.getBytes(StandardCharsets.UTF_8)));

      InputStream svgStream = new ByteArrayInputStream(Objects.requireNonNull(svg_xml.getBytes(StandardCharsets.UTF_8)));
      TranscoderInput input = new TranscoderInput(svgStream);

      PNGTranscoder transcoder = new PNGTranscoder();
      // Setup output
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      TranscoderOutput output = new TranscoderOutput(outStream);

      // Do the transformation
      transcoder.transcode(input, output);
      final byte[] result = outStream.toByteArray();
      repositoryService.addModelEditorSourceExtra(model.getId(), result);
      outStream.close();

    } catch (Exception e) {
      throw new RuntimeException("Error saving model", e);
    }
  }
}
