package com.murrayc.murraycgwtpexample.client.application.thing;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.murrayc.murraycgwtpexample.client.Log;
import com.murrayc.murraycgwtpexample.client.ThingService;
import com.murrayc.murraycgwtpexample.shared.Thing;
import com.murrayc.murraycgwtpexample.client.MurraycGwtpExampleConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murrayc on 1/21/16.
 */
public class ThingView extends ViewWithUiHandlers<ThingUserEditUiHandlers>
        implements ThingPresenter.MyView {
    // *Constants.java is generated in the target/ directory,
    // from *Constants.properties
    // by the gwt-maven-plugin's i18n (mvn:i18n) goal.
    private final MurraycGwtpExampleConstants constants = GWT.create(MurraycGwtpExampleConstants.class);

    private String choiceSelected;

    private final Label thingLabel = new Label("");
    private final Panel choicesPanel = new VerticalPanel();

    private final FlowPanel resultPanel = new FlowPanel();
    private final Button nextThingButton = new Button("Next");
    private final Label resultLabel = new Label();

    ThingView() {
        final FlowPanel mainPanel = new FlowPanel();
        mainPanel.addStyleName("content-panel");

        final Label titleLabel = new Label(constants.thingTitle());
        titleLabel.addStyleName("page-title-label");
        mainPanel.add(titleLabel);

        mainPanel.add(thingLabel);
        thingLabel.addStyleName("thing-label");
        mainPanel.add(choicesPanel);
        choicesPanel.addStyleName("choices-panel");

        resultPanel.addStyleName("result-panel");
        resultPanel.add(resultLabel);
        resultLabel.addStyleName("result-label");

        resultPanel.add(nextThingButton);
        nextThingButton.addStyleName("next-thing-button");

        nextThingButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                onNextThingButton();
            }
        });
        mainPanel.add(resultPanel);

        initWidget(mainPanel);
    }

    private void onNextThingButton() {
        getUiHandlers().onGoToNextThing();
    }

    @Override
    public void setThing(final Thing thing) {
        Window.setTitle("murrayc GWTP AppEngine Example" + ": " + "Thing" + ": " + thing.getText());

        choicesPanel.clear();

        if (thing == null) {
            thingLabel.setText("");
            return;
        }

        thingLabel.setText(thing.getText());

        List<String> choices = new ArrayList<>();
        choices.add("Yes");
        choices.add("No");
        final String groupName = "choices";
        for (final String choice : choices) {
            final RadioButton radioButton = new RadioButton(groupName, choice);
            radioButton.addStyleName("thing-radio-button");
            radioButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(final ValueChangeEvent<Boolean> event) {
                    if (event.getValue()) {
                        submitAnswer(choice);
                    }
                }
            });

            choicesPanel.add(radioButton);
        }

        updateResultPanelUi(State.WAITING_FOR_ANSWER);
        resultLabel.setText("");
    }

    @Override
    public String getChoiceSelected() {
        return choiceSelected;
    }

    @Override
    public void setSubmissionResult(final ThingService.SubmissionResult submissionResult) {
        if (submissionResult == null) {
            Log.error("setSubmissionResult(): submissionResult was null.");
            return;
        }

        updateResultPanelUi(submissionResult.getResult() ? State.CORRECT_ANSWER : State.WRONG_ANSWER);
    }

    private void submitAnswer(final String answer) {
        choiceSelected = answer;
        getUiHandlers().onSubmitAnswer();
    }

    private void updateResultPanelUi(final State state) {
        enableChoices(true);

        switch (state) {
            case WAITING_FOR_ANSWER: {
                resultLabel.setVisible(false);
                break;
            }
            case WRONG_ANSWER: {
                resultLabel.setText("Wrong");
                resultLabel.setVisible(true);
                break;
            }
            case CORRECT_ANSWER: {
                //Don't let them immediately submit another correct answer,
                //because they now know the correct answer.
                enableChoices(false);

                resultLabel.setText("Correct");
                resultLabel.setVisible(true);
            }
        }
    }

    /** Disable all radio buttons in the the choicesPanel.
     * We need this helper method because there is no general Panel.setEnabled() method.
     *
     * @param enabled
     */
    private void enableChoices(boolean enabled) {
        for (Widget widget : choicesPanel) {
            if (widget instanceof RadioButton) {
                final RadioButton radioButton = (RadioButton) widget;
                radioButton.setEnabled(enabled);
            }
        }
    }

    private enum State {
        WAITING_FOR_ANSWER,
        WRONG_ANSWER,
        CORRECT_ANSWER
    }
}
